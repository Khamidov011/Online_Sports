package org.example.online_sports.service;


import lombok.RequiredArgsConstructor;

import org.example.online_sports.entity.Users;
import org.example.online_sports.entity.enums.Role_Enum;
import org.example.online_sports.exceptions.NotFoundException;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.repository.RoleRepository;
import org.example.online_sports.repository.UserRepository;
import org.example.online_sports.request.AuthLogin;
import org.example.online_sports.request.AuthRegister;
import org.example.online_sports.response.Token;
import org.example.online_sports.security.JWTProvider;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MailSender javaMailSender;
    private final JWTProvider jWTProvider;

    public ApiResponse register(AuthRegister authRegister) {
        // Bu to'liq CRUD qism✅
        boolean exists = userRepository.existsByEmail(authRegister.getEmail());
        if (exists) {
            return new ApiResponse("This email already used", false, HttpStatus.BAD_REQUEST, null);
        }
        long code = Math.round(Math.random() * 100000);
        System.out.println(code);

        Users users = Users.builder()
                .fullName(authRegister.getFullName())
                .email(authRegister.getEmail())
                .phoneNumber(authRegister.getPhoneNumber())
                .password(passwordEncoder.encode(authRegister.getPassword()))
                .role(roleRepository.findByRole(Role_Enum.ROLE_USER))
                .enabled(false)
                .build();
        userRepository.save(users);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("y12342433t@gamil.com");
        simpleMailMessage.setTo(authRegister.getEmail());
        simpleMailMessage.setSubject("Verify your email");
        simpleMailMessage.setText("Your verification code: " + code);
        simpleMailMessage.setText("Now you need to process this code into the system");
        javaMailSender.send(simpleMailMessage);
        return new ApiResponse("You have registered. Now verify the code", true, HttpStatus.OK, null);

    }

    public ApiResponse activateUser(Long code) {
        Users users = userRepository.findByCode(code).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        users.setEnabled(true);
        userRepository.save(users);

        String token = jWTProvider.generateToken(users.getEmail());
        Token tokenObj = Token.builder()
                .token(token)
                .role(users.getRole().getRole().name())
                .build();
        return new ApiResponse("You have been verified. Now you can log in freely", true, HttpStatus.OK, tokenObj);
    }

    public ApiResponse login(AuthLogin authLogin) {
        Users users = userRepository.findByEmail(authLogin.getEmail()).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        if (users.isEnabled()) {
            if (passwordEncoder.matches(authLogin.getPassword(), users.getPassword())) {

            }
            if (authLogin.getPassword().equals(users.getPassword())) {
                String token = jWTProvider.generateToken(users.getEmail());
                Token token1 = Token.builder()
                        .token(token)
                        .role(users.getRole().getRole().name())
                        .build();
                return new ApiResponse("Login successful", true, HttpStatus.OK, token1);
            }
            return new ApiResponse("The password you entered is incorrect", false, HttpStatus.BAD_REQUEST, null);
        }
        return new ApiResponse("You are not active yet", false, HttpStatus.BAD_REQUEST, null);

    }
}