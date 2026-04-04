package org.example.online_sports.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResStudent {
    private Long id;

    private String fullName;

    private int age;

    private String gender;

    private String address;

    private String status;
}
