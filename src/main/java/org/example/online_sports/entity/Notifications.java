package org.example.online_sports.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.online_sports.entity.template.AbsEntity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Notifications extends AbsEntity {

    private String userId;

    private String title;

    private String message;

    private boolean isRead;

    private String sandAt;
}
