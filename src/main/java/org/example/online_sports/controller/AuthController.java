package org.example.online_sports.controller;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.request.AuthLogin;
import org.example.online_sports.request.AuthRegister;
import org.example.online_sports.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody AuthRegister authRegister) {
        ApiResponse register = authService.register(authRegister);
        return ResponseEntity.ok(register);
    }

    @PutMapping("/activate")
    public ResponseEntity<ApiResponse> activateUser(@RequestParam Long code) {
        ApiResponse activateUser = authService.activateUser(code);
        return ResponseEntity.ok(activateUser);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthLogin authLogin) {
        ApiResponse login = authService.login(authLogin);
        return ResponseEntity.ok(login);
    }
}