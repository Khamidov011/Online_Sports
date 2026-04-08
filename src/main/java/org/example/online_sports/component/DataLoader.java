package org.example.online_sports.component;

import lombok.RequiredArgsConstructor;
import org.example.online_sports.entity.Roles;
import org.example.online_sports.entity.Users;
import org.example.online_sports.entity.enums.Role_Enum;
import org.example.online_sports.repository.RoleRepository;
import org.example.online_sports.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (ddl.equals("create") || ddl.equals("create-drop")) {
            Roles admin = Roles.builder()
                    .role(Role_Enum.ROLE_ADMIN)
                    .build();
            roleRepository.save(admin);

            Roles teacher = Roles.builder()
                    .role(Role_Enum.ROLE_TEACHER)
                    .build();
            roleRepository.save(teacher);
            Roles user = Roles.builder()
                    .role(Role_Enum.ROLE_USER)
                    .build();
            roleRepository.save(user);


            Users users = Users.builder()
                    .fullName("Admin admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .phoneNumber("+998914676507")
                    .enabled(true)
                    .role(admin)
                    .build();
            userRepository.save(users);
        }
    }
}
