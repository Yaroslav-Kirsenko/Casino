package com.example.Casino.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

//      MAIN
    @GetMapping("/main")
    public String mainPage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "main";
    }
//    @GetMapping("/main")
//    public String mainPage(Model model) {
//        return "main";
//    }

    @GetMapping("/slot")
    public String slot(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/main";
        }
        return "slot";
    }
}
