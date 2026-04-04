package org.example.online_sports.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private String message;

    private boolean success;

    private HttpStatus status;

    private Object body;

}
