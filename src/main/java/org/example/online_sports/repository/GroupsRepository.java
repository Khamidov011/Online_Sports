package org.example.online_sports.repository;

import org.example.online_sports.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

    // Ism mavjudligini tekshiradi✅
    boolean existsByName(String name);

    // Ism va Id mavjudligini tekshiradi✅
    boolean existsByNameAndIdNot(String name, Long id);

    // Id ni qidirish✅
    Optional<Groups> findById(Long id);

}