package com.example.Casino.Controller;

import com.example.Casino.Model.AdminUser;
import com.example.Casino.Model.User;
import com.example.Casino.Repo.AdminUserRepository;
import com.example.Casino.Repo.UserRepository;
import com.example.Casino.Service.AdminUserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminUserController {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private  UserRepository userRepository;

    //  LOGIN PANEL
    @GetMapping("/login-admin")
    public String getLoginPage() {
        return "login-admin";
    }

    @PostMapping("/login-admin")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        AdminUser user = adminUserService.authenticate(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            model.addAttribute("message", "User successfully authenticated");
            return "redirect:/users";
        } else {
            model.addAttribute("message", "Authentication failed");
            return "login-admin";
        }
    }

    // ADMIN PANEL
    @GetMapping("/users")
    public String usersPage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login-admin";
        }
        List<User> listOfUsers = userRepository.findAll();
        model.addAttribute("user", listOfUsers);
        return "user";
    }
}

