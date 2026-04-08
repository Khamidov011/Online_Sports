package org.example.online_sports.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResUser {
    private Long id;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String imageUrl;

    private String role;


}
