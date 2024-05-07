package com.example.Casino.Controller;


import com.example.Casino.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StrartController {

    private final UserService userService;

    @GetMapping("/")
    public String index() throws IOException {
        Resource resource = new ClassPathResource("templates/index.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }


    @GetMapping("/main")
    public String main() throws IOException {
        Resource resource = new ClassPathResource("templates/main.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }

    @PostMapping("/main")
    public ResponseEntity<String> mainBalance() {
        try {

            String balance = userService.getCurrentUserBalance();

            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching balance");
        }
    }


    @PostMapping("/update-balance")
    public ResponseEntity<String> updateBalance(@RequestBody String newBalance) {
        try {
            if (newBalance == null || newBalance.isEmpty()) {
                return ResponseEntity.badRequest().body("Новый баланс не указан");
            }

            userService.updateCurrentUserBalance(newBalance);


            return ResponseEntity.ok("Баланс успешно обновлен");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при обновлении баланса");
        }
    }



    @GetMapping("/slot1")
    public String slot1() throws IOException {
        Resource resource = new ClassPathResource("templates/slot1.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }

    @GetMapping("/slot2")
    public String slot2() throws IOException {
        Resource resource = new ClassPathResource("templates/slot2.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }

    @GetMapping("/slot3")
    public String slot3() throws IOException {
        Resource resource = new ClassPathResource("templates/slot3.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }

    @GetMapping("/roulette1")
    public String roulette1() throws IOException {
        Resource resource = new ClassPathResource("templates/roulette1.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }
    @GetMapping("/roulette2")
    public String roulette2() throws IOException {
        Resource resource = new ClassPathResource("templates/roulette2.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }

    @GetMapping("/crash1")
    public String crash() throws IOException {
        Resource resource = new ClassPathResource("templates/crash1.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }

}
