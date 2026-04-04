package org.example.online_sports.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResGroup {
    private String name;

    private int capacity;

    private String level;
}
