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
public class ResSportType {
    private Long id;

    private String name;

    private String category;

    private int minAge;

    private int maxAge;

    private String description;

    private boolean isActive;
}
