package org.example.online_sports.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.online_sports.entity.enums.Role_Enum;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role_Enum role;

    @Override
    public @Nullable String getAuthority() {
        return role.name();
    }
}