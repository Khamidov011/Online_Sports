package org.example.online_sports.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.online_sports.entity.template.AbsEntity;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class notifications extends AbsEntity {

    private String title;

    private String message;

    private boolean isRead;

    private Time sendAt;

    @ManyToOne
    private Users user;
}