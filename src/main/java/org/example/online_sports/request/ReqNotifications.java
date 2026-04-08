package org.example.online_sports.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqNotifications {
    @Schema(hidden = true)
    private Long id;

    @NotBlank(message = "Title bo'sh bo'lmasin")
    private String title;

    @NotBlank(message = "message bo'sh bo'lmasin")
    private String message;

}
