package org.example.online_sports.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.online_sports.entity.template.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Sports_type extends AbsEntity {

    private String name;

    private String category;

    private int minAge;

    private int maxAge;

    private String description;

    private boolean isActive;
}
