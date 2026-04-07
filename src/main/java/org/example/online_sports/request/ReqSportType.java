package org.example.online_sports.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqSportType {
    @Schema(hidden = true)
    private Long id;

    @NotBlank(message = "Nomi bo'sh bo'lmasin")
    private String name;

    private String category;

    private int minAge;

    private int maxAge;

    private String description;

    private boolean isActive;

}
