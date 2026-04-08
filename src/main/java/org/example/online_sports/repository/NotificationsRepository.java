package org.example.online_sports.repository;

import org.example.online_sports.entity.Groups;
import org.example.online_sports.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    boolean existsByTitle(String title);

    boolean existsByTitleAndIdNot(String title, Long id);


    Optional<Notifications> findById(Long id);


}