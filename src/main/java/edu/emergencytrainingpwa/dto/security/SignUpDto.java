package edu.emergencytrainingpwa.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static edu.emergencytrainingpwa.constant.AppConstant.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    @Pattern(
        regexp = USERNAME_REGEXP,
        message = USERNAME_MESSAGE)
    private String username;

    @Pattern(
        regexp = NAME_REGEXP,
        message = WRONG_FIRST_NAME_FORMAT)
    private String firstName;

    @Pattern(
        regexp = NAME_REGEXP,
        message = WRONG_LAST_NAME_FORMAT)
    private String lastName;

    @Pattern(
        regexp = NAME_REGEXP,
        message = WRONG_PATRONYMIC_NAME_FORMAT)
    private String patronymicName;

    @NotBlank
    @Email(
        regexp = EMAIL_REGEXP,
        message = INVALID_EMAIL)
    private String email;

    @NotBlank
    @Pattern(
        regexp = PASSWORD_REGEXP,
        message = WRONG_PASSWORD_FORMAT)
    private String password;
}
