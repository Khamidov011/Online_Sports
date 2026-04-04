package org.example.online_sports.repository;

import org.example.online_sports.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    // Email orqali topadi✅
    Optional<Users> findByEmail(String email);

    // email mavjudligini tekshiradi✅
    boolean existsByEmail(String email);

    // Code orqali tekshirish✅
    Optional<Users> findByCode(Long code);
}