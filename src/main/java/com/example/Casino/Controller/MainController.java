//package com.example.Casino.Controller;
//
//
////import com.example.Casino.Model.User;
////import com.example.Casino.Repo.UserRepository;
////import com.example.Casino.Service.UserService;
//
//import jakarta.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//@Controller
//public class MainController {
//
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private UserService userService;
//
////      MAIN
////    @GetMapping("/main")
////    public String mainPage(Model model, HttpSession session) {
////        if (session.getAttribute("user") == null) {
////            return "redirect:/login";
////        }
////        return "main";
////    }
//    @GetMapping("/main")
//    public String mainPage(Model model) {
//        return "main";
//    }
//
//    @GetMapping("/slot1")
//    public String slotGame1(Model model, HttpSession session) {
////        if (session.getAttribute("user") == null) {
////            return "redirect:/main";
////        }
//        return "Game/Slot/slot1.html";
//    }
//
//    @GetMapping("/slot2")
//    public String slotGame2(Model model, HttpSession session) {
////        if (session.getAttribute("user") == null) {
////            return "redirect:/main";
////        }
//        return "Game/Slot/slot2.html";
//    }
//
//    @GetMapping("/slot3")
//    public String slotGame3(Model model, HttpSession session) {
////        if (session.getAttribute("user") == null) {
////            return "redirect:/main";
////        }
//        return "Game/Slot/slot3.html";
//    }
//
//    @GetMapping("/roulette1")
//    public String slotRoullete1(Model model, HttpSession session) {
////        if (session.getAttribute("user") == null) {
////            return "redirect:/main";
////        }
//        return "Game/Roulette/roulette1.html";
//    }
//    @GetMapping("/roulette2")
//    public String slotRoullete2(Model model, HttpSession session) {
////        if (session.getAttribute("user") == null) {
////            return "redirect:/main";
////        }
//        return "Game/Roulette/roulette2.html";
//    }
//
//    @GetMapping("/crash1")
//    public String slotCrash1(Model model, HttpSession session) {
////        if (session.getAttribute("user") == null) {
////            return "redirect:/main";
////        }
//        return "Game/Crash/crash1.html";
//    }
//
//
////    @PostMapping("/updatebalance")
////    public ResponseEntity<String> updateBalance(@RequestBody Map<String, Object> payload) {
////        try {
////            String username = (String) payload.get("username");
////            int amount = (int) payload.get("balance");
////
////            User user = userRepository.findUserByUsername(username);
////            if (user == null) {
////                return ResponseEntity.badRequest().body("Player not found");
////            }
////
////            int currentBalance = user.getBalance();
////            int updatedBalance = currentBalance + amount;
////            user.setBalance(updatedBalance);
////            userRepository.save(user);
////
////            return ResponseEntity.ok("Balance updated successfully");
////        } catch (Exception e) {
////            return ResponseEntity.status(500).body("Error updating balance");
////        }
////    }
//}