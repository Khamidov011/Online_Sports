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
public class ReqUser {
    @Schema(hidden = true)
    private Long id;


    @NotBlank(message = "Ism familiya bo'sh bo'lmasin")
    private String fullName;

    @Email(message = "Email bo'sh bo'lmasin")
    private String email;

    @Pattern(regexp = "^998(9[012345789]|6[125679]|7[01234569])[0-9]{7}$",
        message = "Telefon raqam formati xato")
    private String phoneNumber;

    @NotBlank
    private String password;
}
