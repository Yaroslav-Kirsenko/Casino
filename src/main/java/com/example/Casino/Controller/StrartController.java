package com.example.Casino.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StrartController {

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
