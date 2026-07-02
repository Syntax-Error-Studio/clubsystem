package com.clubsystem.controller;

import com.clubsystem.bean.*;
import com.clubsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SignupService signupService;

    @Autowired
    private CheckinService checkinService;

    @Autowired
    private NoticeService noticeService;

    // ==================== 活动管理 ====================

    @GetMapping("/activities")
    public String activityList(Model model) {
        model.addAttribute("activities", activityService.listAllActivities());
        return "admin/activity-list";
    }

    @GetMapping("/activity/form")
    public String activityForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            model.addAttribute("activity", activityService.getActivityById(id));
        }
        return "admin/activity-form";
    }

    @PostMapping("/activity/save")
    public String saveActivity(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("location") String location,
            @RequestParam("maxCount") int maxCount,
            @RequestParam("status") String status,
            @RequestParam("categoryId") int categoryId,
            @RequestParam(value = "coverImageFile", required = false) MultipartFile coverImageFile,
            HttpSession session,
            RedirectAttributes ra) {

        User currentUser = (User) session.getAttribute("currentUser");
        Activity activity;

        if (id != null) {
            activity = activityService.getActivityById(id);
        } else {
            activity = new Activity();
            activity.setCreateTime(new Date());
            activity.setCurrentCount(0);
            activity.setCreatorId(currentUser.getId());
        }

        activity.setTitle(title);
        activity.setContent(content);
        activity.setLocation(location);
        activity.setMaxCount(maxCount);
        activity.setStatus(status);
        activity.setCategoryId(categoryId);

        // 处理文件上传
        if (coverImageFile != null && !coverImageFile.isEmpty()) {
            String originalName = coverImageFile.getOriginalFilename();
            String ext = "";
            if (originalName != null && originalName.contains(".")) {
                ext = originalName.substring(originalName.lastIndexOf("."));
            }
            // 限制文件类型
            if (!ext.matches("\\.(?i)(jpg|jpeg|png|gif|bmp)")) {
                ra.addFlashAttribute("error", "封面图片仅支持 jpg、jpeg、png、gif、bmp 格式");
                return "redirect:/admin/activity/form" + (id != null ? "?id=" + id : "");
            }
            // 限制文件大小（5MB）
            if (coverImageFile.getSize() > 5 * 1024 * 1024) {
                ra.addFlashAttribute("error", "封面图片大小不能超过 5MB");
                return "redirect:/admin/activity/form" + (id != null ? "?id=" + id : "");
            }

            String newName = UUID.randomUUID().toString() + ext;
            String uploadDir = session.getServletContext().getRealPath("/uploads");
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();
            try {
                coverImageFile.transferTo(new File(dir, newName));
                activity.setCoverImage("/uploads/" + newName);
            } catch (IOException e) {
                ra.addFlashAttribute("error", "文件上传失败：" + e.getMessage());
                return "redirect:/admin/activity/form" + (id != null ? "?id=" + id : "");
            }
        }

        boolean ok;
        if (id != null) {
            ok = activityService.updateActivity(activity);
        } else {
            ok = activityService.addActivity(activity);
        }

        ra.addFlashAttribute("message", ok ? "操作成功" : "操作失败");
        return "redirect:/admin/activities";
    }

    @PostMapping("/activity/delete")
    public String deleteActivity(@RequestParam("id") int id, RedirectAttributes ra) {
        boolean ok = activityService.deleteActivity(id);
        ra.addFlashAttribute("message", ok ? "删除成功" : "删除失败");
        return "redirect:/admin/activities";
    }

    // ==================== 报名管理 ====================

    @GetMapping("/signups")
    public String signupList(@RequestParam(value = "activityId", required = false) Integer activityId, Model model) {
        model.addAttribute("activities", activityService.listAllActivities());
        if (activityId != null) {
            model.addAttribute("signups", signupService.listSignupsByActivity(activityId));
            model.addAttribute("selectedActivityId", activityId);
        }
        return "admin/signup-list";
    }

    @PostMapping("/signup/cancel")
    public String cancelSignup(@RequestParam("id") int id,
                               @RequestParam(value = "activityId", required = false) Integer activityId,
                               RedirectAttributes ra) {
        boolean ok = signupService.cancelSignup(id);
        ra.addFlashAttribute("message", ok ? "已取消报名" : "取消失败");
        String redirect = "redirect:/admin/signups";
        if (activityId != null) redirect += "?activityId=" + activityId;
        return redirect;
    }

    // ==================== 签到管理 ====================

    @GetMapping("/checkin")
    public String checkinPage(@RequestParam(value = "activityId", required = false) Integer activityId, Model model) {
        model.addAttribute("activities", activityService.listAllActivities());
        if (activityId != null) {
            model.addAttribute("checkins", checkinService.listCheckinsByActivity(activityId));
            model.addAttribute("selectedActivityId", activityId);
            // 获取该活动的报名列表（未签到的人用于下拉选择）
            model.addAttribute("signups", signupService.listSignupsByActivity(activityId));
        }
        return "admin/checkin";
    }

    @PostMapping("/checkin")
    public String doCheckin(@RequestParam("signupId") int signupId,
                            @RequestParam("activityId") int activityId,
                            HttpSession session,
                            RedirectAttributes ra) {
        User currentUser = (User) session.getAttribute("currentUser");
        // 查重复
        if (checkinService.getCheckinBySignupId(signupId) != null) {
            ra.addFlashAttribute("error", "该报名已签到，请勿重复操作");
            return "redirect:/admin/checkin?activityId=" + activityId;
        }

        Signup signup = signupService.getSignupById(signupId);
        Checkin checkin = new Checkin();
        checkin.setSignupId(signupId);
        checkin.setUserId(signup.getUserId());
        checkin.setActivityId(activityId);
        checkin.setCheckinTime(new Date());
        checkin.setCheckinStatus("已签到");
        checkin.setOperatorId(currentUser.getId());

        boolean ok = checkinService.addCheckin(checkin);
        ra.addFlashAttribute("message", ok ? "签到成功" : "签到失败");
        return "redirect:/admin/checkin?activityId=" + activityId;
    }

    // ==================== 公告管理 ====================

    @GetMapping("/notices")
    public String noticeList(Model model) {
        model.addAttribute("notices", noticeService.listAllNotices());
        return "admin/notice-list";
    }

    @GetMapping("/notice/form")
    public String noticeForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            model.addAttribute("notice", noticeService.getNoticeById(id));
        }
        return "admin/notice-form";
    }

    @PostMapping("/notice/save")
    public String saveNotice(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("status") String status,
            HttpSession session,
            RedirectAttributes ra) {

        User currentUser = (User) session.getAttribute("currentUser");
        Notice notice;

        if (id != null) {
            notice = noticeService.getNoticeById(id);
        } else {
            notice = new Notice();
            notice.setCreateTime(new Date());
            notice.setPublisherId(currentUser.getId());
        }

        notice.setTitle(title);
        notice.setContent(content);
        notice.setStatus(status);

        boolean ok;
        if (id != null) {
            ok = noticeService.updateNotice(notice);
        } else {
            ok = noticeService.addNotice(notice);
        }

        ra.addFlashAttribute("message", ok ? "操作成功" : "操作失败");
        return "redirect:/admin/notices";
    }

    @PostMapping("/notice/delete")
    public String deleteNotice(@RequestParam("id") int id, RedirectAttributes ra) {
        boolean ok = noticeService.deleteNotice(id);
        ra.addFlashAttribute("message", ok ? "删除成功" : "删除失败");
        return "redirect:/admin/notices";
    }

    // ==================== 文件下载：导出报名名单 ====================

    @GetMapping("/signups/export")
    public void exportSignups(@RequestParam("activityId") int activityId,
                              HttpServletResponse response) throws IOException {
        Activity activity = activityService.getActivityById(activityId);
        String title = activity != null ? activity.getTitle() : "活动" + activityId;
        List<Signup> signups = signupService.listSignupsByActivity(activityId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder csv = new StringBuilder();
        // BOM 让 Excel 正确识别 UTF-8
        csv.append("\uFEFF");
        csv.append("报名ID,学号,姓名,手机号,报名时间,状态\n");
        if (signups != null) {
            for (Signup s : signups) {
                User u = s.getUser();
                csv.append(s.getId()).append(",");
                csv.append(u != null && u.getStudentNo() != null ? u.getStudentNo() : "").append(",");
                csv.append(u != null ? u.getRealName() : "").append(",");
                csv.append(u != null && u.getPhone() != null ? u.getPhone() : "").append(",");
                csv.append(s.getSignupTime() != null ? sdf.format(s.getSignupTime()) : "").append(",");
                csv.append(s.getStatus() != null ? s.getStatus() : "").append("\n");
            }
        }

        String fileName = URLEncoder.encode(title + "_报名名单.csv", "UTF-8");
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setCharacterEncoding("UTF-8");
        OutputStream os = response.getOutputStream();
        os.write(csv.toString().getBytes("UTF-8"));
        os.flush();
    }
}
