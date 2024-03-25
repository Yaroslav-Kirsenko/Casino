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

    @GetMapping("/slot1")
    public String slotGame1(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "Game/Slot/slot1.html";
    }

    @GetMapping("/slot2")
    public String slotGame2(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "Game/Slot/slot2.html";
    }

    @GetMapping("/slot3")
    public String slotGame3(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "Game/Slot/slot3.html";
    }

    @GetMapping("/roulette1")
    public String slotRoullete(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "Game/Roulette/roulette1.html";
    }
    @GetMapping("/roulette2")
    public String slotRoullete1(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/main";
//        }
        return "Game/Roulette/roulette2.html";
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
