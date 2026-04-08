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
public class Students extends AbsEntity {

    private String fullName;

    private int age;

    private String gender;

    private String address;

    private String status;

    @ManyToOne
    private Groups group;

    @ManyToOne
    private Users user;
}
