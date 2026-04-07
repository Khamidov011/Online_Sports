package org.example.online_sports.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResNotifications {
    private Long id;

    private String title;

    private String message;

    private boolean isRead;

    private Time sendAt;
}
