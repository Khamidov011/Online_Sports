package org.example.online_sports.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReqGroup {

    private Long id;

    private String name;

    private int capacity;

    private String level;
}