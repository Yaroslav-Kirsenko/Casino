package com.example.Casino.Controller;

import com.example.Casino.DTO.JwtAuthenticationResponse;
import com.example.Casino.DTO.SignInRequest;
import com.example.Casino.DTO.SignUpRequest;
import com.example.Casino.Service.AuthenticationService;
import com.example.Casino.Service.JwtService;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    @GetMapping("/sign-up")
    public String signUp() throws IOException {
        // Читаем файл index.html из папки resources/static
        Resource resource = new ClassPathResource("templates/register.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }


    @GetMapping("/sign-in")
    public String signIn() throws IOException {
        // Читаем файл index.html из папки resources/static
        Resource resource = new ClassPathResource("templates/login.html");
        String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return htmlContent;
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @GetMapping("/balance")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public ResponseEntity<Map<String, Object>> getBalance(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Убираем префикс "Bearer "
            Claims claims = jwtService.extractAllClaims(token);
            String balance = claims.get("balance", String.class); // Получаем баланс из токена
            Map<String, Object> response = new HashMap<>();
            response.put("balance", balance);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/id")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public ResponseEntity<Map<String, Object>> getId(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Убираем префикс "Bearer "
            Claims claims = jwtService.extractAllClaims(token);
            Integer idInteger = claims.get("id", Integer.class); // Получаем id из токена как Integer
            Long id = idInteger != null ? idInteger.longValue() : null; // Преобразуем Integer в Long
            if (id != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("id", id);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @GetMapping("/username")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public ResponseEntity<Map<String, Object>> getUsername(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Убираем префикс "Bearer "
            Claims claims = jwtService.extractAllClaims(token);
            String username = claims.getSubject(); // Получаем имя пользователя из токена
            Map<String, Object> response = new HashMap<>();
            response.put("username", username);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }



    @GetMapping("/password")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public ResponseEntity<Map<String, Object>> getPassword(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Убираем префикс "Bearer "
            Claims claims = jwtService.extractAllClaims(token);
            String password = claims.get("password", String.class); // Получаем баланс из токена
            Map<String, Object> response = new HashMap<>();
            response.put("password", password);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

//    @PostMapping("/refreshToken")
//    public JwtAuthenticationResponse refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
//        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getUserInfo)
//                .map(userInfo -> {
//                    String accessToken = jwtService.GenerateToken(userInfo.getUsername());
//                    return JwtResponseDTO.builder()
//                            .accessToken(accessToken)
//                            .token(refreshTokenRequestDTO.getToken()).build();
//                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
//    }

}