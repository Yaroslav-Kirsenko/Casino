package com.example.Casino.Controller;

import com.example.Casino.Model.User;
import com.example.Casino.Repo.UserRepository;
import com.example.Casino.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private UserService userService;

    //  START POINT
    @GetMapping("/")
    public String index() {
        return "index";
    }

    //  REGISTRATION PANEL
    @GetMapping("/register")
    public String getRegPage(@ModelAttribute("user") User user) {
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        userRepository.save(user);
        model.addAttribute("message", "Submitted Successfully");
        return "register";
    }

    //  LOGIN PANEL
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            model.addAttribute("message", "User successfully authenticated");
            return "redirect:/main";
        } else {
            model.addAttribute("message", "Authentication failed");
            return "login";
        }
    }
}