package org.example.online_sports.service;



import lombok.RequiredArgsConstructor;
import org.example.online_sports.entity.Users;
import org.example.online_sports.entity.enums.Role_Enum;
import org.example.online_sports.mapper.UserMapper;
import org.example.online_sports.payload.ApiResponse;
import org.example.online_sports.repository.RoleRepository;
import org.example.online_sports.repository.UserRepository;
import org.example.online_sports.request.AuthRegister;
import org.example.online_sports.request.ReqUser;
import org.example.online_sports.response.Token;
import org.example.online_sports.security.JWTProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper usersMapper;
    private final JWTProvider jWTProvider;


    public ApiResponse getMe(Users users){
        return ApiResponse.builder()
                .message("success")
                .status(HttpStatus.OK)
                .success(true)
                .body(usersMapper.resUser(users))
                .build();
    }
    public ApiResponse saveTeacher(AuthRegister authRegister){
        boolean exists = userRepository.existsByEmail(authRegister.getEmail());
        if (exists){
            return ApiResponse.builder()
                    .message("Teacher already exists")
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null)
                    .build();
        }

        Users user = Users.builder()
                .fullName(authRegister.getFullName())
                .email(authRegister.getEmail())
                .phoneNumber(authRegister.getPhoneNumber())
                .password(passwordEncoder.encode(authRegister.getPassword()))
                .role(roleRepository.findByRole(Role_Enum.ROLE_TEACHER))
                .enabled(true)
                .code(0L)
                .build();
        userRepository.save(user);
        return ApiResponse.builder()
                .message("Teacher Successfully saved")
                .success(true)
                .body(null)
                .build();
    }



    public ApiResponse updateUser(Users user, ReqUser reqUser) {

        if (user.getRole().getRole().equals(Role_Enum.ROLE_TEACHER)) {

            if (reqUser.getId() == null) {

                boolean exists = userRepository.existsByEmail(
                        reqUser.getEmail()
                );

                if (exists) {
                    return ApiResponse.builder()
                            .message("This email already exists")
                            .success(false)
                            .status(HttpStatus.BAD_REQUEST)
                            .body(null)
                            .build();
                }

                user.setFullName(reqUser.getFullName());
                user.setPhoneNumber(reqUser.getPhoneNumber());

                if (reqUser.getEmail().equals(user.getEmail())) {

                    user.setEmail(reqUser.getEmail());
                    Users save = userRepository.save(user);

                    String token = jWTProvider.generateToken(save.getEmail());

                    Token token1 = Token.builder()
                            .token(token)
                            .role(Role_Enum.ROLE_ADMIN.name())
                            .build();

                    return ApiResponse.builder()
                            .message("Success")
                            .status(HttpStatus.OK)
                            .body(token1)
                            .build();
                }

                userRepository.save(user);

                return ApiResponse.builder()
                        .message("Success")
                        .success(true)
                        .body(null)
                        .status(HttpStatus.OK)
                        .build();

            } else {

                user = userRepository.findById(reqUser.getId()).orElse(null);

                if (user == null) {
                    return ApiResponse.builder()
                            .message("User not found")
                            .success(false)
                            .body(null)
                            .status(HttpStatus.NOT_FOUND)
                            .build();
                }

                user.setFullName(reqUser.getFullName());
                user.setPhoneNumber(reqUser.getPhoneNumber());
                user.setEmail(reqUser.getEmail());

                userRepository.save(user);

                return ApiResponse.builder()
                        .message("Success")
                        .success(true)
                        .body(null)
                        .status(HttpStatus.OK)
                        .build();
            }

        } else {

            user.setFullName(reqUser.getFullName());
            user.setPhoneNumber(reqUser.getPhoneNumber());

            if (!reqUser.getEmail().equals(user.getEmail())) {

                user.setEmail(reqUser.getEmail());
                Users save = userRepository.save(user);

                String token = jWTProvider.generateToken(save.getEmail());

                Token token3 = Token.builder()
                        .token(token)
                        .role(user.getRole().getRole().name())
                        .build();

                return ApiResponse.builder()
                        .message("Success")
                        .status(HttpStatus.OK)
                        .body(token3)
                        .build();

            } else {

                userRepository.save(user);

                return ApiResponse.builder()
                        .message("Success")
                        .success(true)
                        .body(null)
                        .status(HttpStatus.OK)
                        .build();
            }
        }
    }


    public ApiResponse deleteUser(Long id){
        Users users = userRepository.findById(id).orElse(null);
        if (users == null){
            return ApiResponse.builder()
                    .message("User not found")
                    .success(false)
                    .status(HttpStatus.NOT_FOUND)
                    .body(null)
                    .build();
        }
        userRepository.delete(users);
        return ApiResponse.builder()
                .message("Success")
                .success(true)
                .status(HttpStatus.OK)
                .body(null)
                .build();
    }

}
