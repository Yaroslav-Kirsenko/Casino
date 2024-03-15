package com.example.Casino.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //  MAIN
    @GetMapping("/main")
    public String helloPage() {
        return "main";
    }

}
