package org.example.online_sports.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.online_sports.entity.template.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Groups extends AbsEntity {

    private String name;

    private int capacity;

    private String level;

    @ManyToOne
    private Sports_type sportsType;
    
}