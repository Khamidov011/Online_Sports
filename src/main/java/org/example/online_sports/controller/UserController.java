package org.example.online_sports.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.online_sports.entity.Users;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.request.AuthRegister;
import org.example.online_sports.request.ReqUser;
import org.example.online_sports.security.CurrentUser;
import org.example.online_sports.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getMe(@CurrentUser Users users){
        ApiResponse me = userService.getMe(users);
        return ResponseEntity.ok(me);
    }

    @PostMapping("/teacher-save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Bu Api faqat ADMIN uchun",
    description = "BU API orqali TEACHER malumotlar saqlanadi")

    public ResponseEntity<ApiResponse> saveTeacher(@RequestBody AuthRegister authRegister){
        ApiResponse apiResponse = userService.saveTeacher(authRegister);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> update(@CurrentUser Users users , @RequestBody ReqUser reqUser){
        ApiResponse apiResponse = userService.updateUser(users,reqUser);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        ApiResponse apiResponse = userService.deleteUser(id);
        return ResponseEntity.ok(apiResponse);
    }

}
