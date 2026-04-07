package org.example.online_sports.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ReqStudent {

    private Long id;

    private String fullName;

    private int age;

    private String gender;

    private String address;

    private String status;

}
