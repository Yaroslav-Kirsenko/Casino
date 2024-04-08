//package com.example.Casino.Controller;
//
//import com.example.Casino.Model.User1;
//import com.example.Casino.Repo.UserRepository;
//import com.example.Casino.Service.UserService1;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import jakarta.servlet.http.HttpSession;
//
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private  UserRepository userRepository;
//
//    @Autowired
//    private UserService1 userService;
//
//    //  START POINT
//    @GetMapping("/")
//    public String index() {
//        return "index.html";
//    }
//
//    //  REGISTRATION PANEL
//    @GetMapping("/register")
//    public String getRegPage(@ModelAttribute("user") User1 user) {
//        return "LoginRegister/register.html";
//    }
//
//    @PostMapping("/register")
//    public String saveUser(@ModelAttribute("user") User1 user, Model model) {
//        userRepository.save(user);
//        model.addAttribute("message", "Submitted Successfully");
//        return "redirect:/login";
//    }
//
//    //  LOGIN PANEL
//    @GetMapping("/login")
//    public String getLoginPage() {
//        return "LoginRegister/login.html";
//    }
//
//    @PostMapping("/login")
//    public String loginUser(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
//        User1 user = userService.authenticate(username, password);
//        if (user != null) {
//            session.setAttribute("user", user);
//            model.addAttribute("message", "User successfully authenticated");
//            return "redirect:/main";
//        } else {
//            model.addAttribute("message", "Authentication failed");
//            return "LoginRegister/login.html";
//        }
//    }
//
//}