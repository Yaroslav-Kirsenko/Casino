package com.example.Casino.Controller;


import com.example.Casino.Repo.UserRepository;
import com.example.Casino.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//      MAIN
//    @GetMapping("/main")
//    public String mainPage(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/login";
//        }
//        return "main";
//    }
    @GetMapping("/main")
    public String mainPage(Model model) {
        return "main";
    }

    @GetMapping("/game1")
    public String slotGame1(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "game1";
    }

    @GetMapping("/game2")
    public String slotGame2(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "game2";
    }

    @GetMapping("/game3")
    public String slotGame3(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "game3";
    }

    @GetMapping("/roulette")
    public String slotRoullete(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "roulette";
    }
    @GetMapping("/roulette1")
    public String slotRoullete1(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "roulette1";
    }








//    @PostMapping("/updateBalance")
//    public ResponseEntity<String> updateBalance(@RequestParam String username, @RequestParam int amount) {
//        try {
//            User user = userRepository.findByUsername(username);
//            if (user == null) {
//                return ResponseEntity.badRequest().body("Player not found");
//            }
//
//            int currentBalance = user.getBalance();
//            int updatedBalance = currentBalance + amount;
//            user.setBalance(updatedBalance);
//            userRepository.save(user);
//
//            return ResponseEntity.ok("Balance updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error updating balance");
//        }
//    }

}
