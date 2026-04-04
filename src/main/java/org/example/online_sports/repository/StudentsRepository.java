package org.example.online_sports.repository;

import org.example.online_sports.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository <Students, Long> {
    boolean existsByFullNameIgnoreCase(String name);

    boolean existsByFullNameAndIdNot(String name, Long id);

}
