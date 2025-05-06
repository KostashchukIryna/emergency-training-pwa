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

import static edu.emergencytrainingpwa.constant.AppConstant.EMAIL_REGEXP;
import static edu.emergencytrainingpwa.constant.AppConstant.INVALID_EMAIL;
import static edu.emergencytrainingpwa.constant.AppConstant.PASSWORD_REGEXP;
import static edu.emergencytrainingpwa.constant.AppConstant.USERNAME_MESSAGE;
import static edu.emergencytrainingpwa.constant.AppConstant.USERNAME_REGEXP;
import static edu.emergencytrainingpwa.constant.AppConstant.WRONG_PASSWORD_FORMAT;

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
    private String name;

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
