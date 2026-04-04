package org.example.online_sports.repository;

import org.example.online_sports.entity.Roles;
import org.example.online_sports.entity.enums.Role_Enum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByRole(Role_Enum roleEnum);
}