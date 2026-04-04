package org.example.online_sports.repository;

import org.example.online_sports.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    Optional<Groups> findById(Long id);

}