package org.example.online_sports.repository;

import org.example.online_sports.entity.Notifications;
import org.example.online_sports.entity.Sports_type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportTypeRepository extends JpaRepository<Sports_type, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);


    Optional<Sports_type> findById(Long id);


}