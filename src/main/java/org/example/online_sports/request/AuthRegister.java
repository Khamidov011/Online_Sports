package org.example.online_sports.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRegister {
    @NotBlank(message = "\n" +
            "First and last name must not be empty")
    private String fullName;

    @Email(message = "Invalid email entered!")
    private String email;

    @Pattern(regexp = "^998(9[012345789]|6[125679]|7[01234569])[0-9]{7}$", message = "\n" +
            "The phone number format is incorrect or an error was entered")
    private String phoneNumber;

    @NotBlank
    private String password;
}