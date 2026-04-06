package org.example.online_sports.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    private String token;

    private String role;

}