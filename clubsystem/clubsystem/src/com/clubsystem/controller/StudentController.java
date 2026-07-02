package com.clubsystem.controller;

import com.clubsystem.bean.Activity;
import com.clubsystem.bean.Signup;
import com.clubsystem.bean.User;
import com.clubsystem.service.ActivityService;
import com.clubsystem.service.CheckinService;
import com.clubsystem.service.NoticeService;
import com.clubsystem.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 学生端控制器。
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ActivityService activityService;

    @Autowired
    private SignupService signupService;

    @Autowired
    private CheckinService checkinService;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/activities")
    public String activities(Model model) {
        List<Activity> activities = activityService.listAllActivities();
        model.addAttribute("activities", activities);
        return "student/activities";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("activityId") int activityId,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        boolean ok = signupService.signupActivity(currentUser.getId(), activityId);
        if (ok) {
            redirectAttributes.addAttribute("message", "报名成功");
            return "redirect:/student/activities";
        }
        redirectAttributes.addAttribute("error", "你已报名该活动或活动已满");
        return "redirect:/student/activities";
    }

    @GetMapping("/my-signups")
    public String mySignups(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        List<Signup> signups = signupService.listSignupsByUser(currentUser.getId());
        model.addAttribute("signups", signups);
        return "student/my-signups";
    }

    @GetMapping("/my-checkins")
    public String myCheckins(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("checkins", checkinService.listCheckinsByUser(currentUser.getId()));
        return "student/my-checkins";
    }

    @GetMapping("/notices")
    public String notices(Model model) {
        model.addAttribute("notices", noticeService.listActiveNotices());
        return "student/notices";
    }
}


