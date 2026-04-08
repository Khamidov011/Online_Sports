package org.example.online_sports.mapper;

import org.example.online_sports.entity.Users;
import org.example.online_sports.response.ResUser;
import org.springframework.stereotype.Component;



@Component
public class UserMapper {
    public ResUser resUser(Users users){
        return ResUser.builder()
                .id(users.getId())
                .fullName(users.getFullName())
                .email(users.getEmail())
                .phoneNumber(users.getPhoneNumber())
                .role(users.getRole() !=null ? users.getRole().getRole().name() : null)
                .build();
    }
}
