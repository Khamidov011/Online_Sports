package org.example.online_sports.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.online_sports.entity.template.AbsEntity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notifications extends AbsEntity {

    private String userId;

    private String title;

    private String message;

    private boolean isRead;

    private String sandAt;
}
