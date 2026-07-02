package com.clubsystem.controller;

import com.clubsystem.bean.User;
import com.clubsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 认证控制器，处理登录、注册和退出登录。
 */
@Controller
@RequestMapping
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpSession session,
                         Model model) {
        User user = userService.login(username, password);
        if (user == null) {
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }

        session.setAttribute("currentUser", user);
        String role = user.getRole();
        if ("admin".equals(role) || "superadmin".equals(role)) {
            return "redirect:/admin/index.jsp";
        }
        return "redirect:/student/index.jsp";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("realName") String realName,
                           @RequestParam(value = "studentNo", required = false) String studentNo,
                           @RequestParam(value = "phone", required = false) String phone,
                           Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setStudentNo(studentNo);
        user.setPhone(phone);

        if (!userService.register(user)) {
            model.addAttribute("error", "注册失败：用户名已存在或填写信息不完整。");
            return "register";
        }

        model.addAttribute("message", "注册成功，请登录！");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}

