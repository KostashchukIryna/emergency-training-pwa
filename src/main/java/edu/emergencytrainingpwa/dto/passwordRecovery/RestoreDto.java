package edu.emergencytrainingpwa.dto.passwordRecovery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static edu.emergencytrainingpwa.constant.AppConstant.PASSWORD_REGEXP;
import static edu.emergencytrainingpwa.constant.AppConstant.WRONG_PASSWORD_FORMAT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestoreDto {
    @NotBlank
    @Pattern(
        regexp = PASSWORD_REGEXP,
        message = WRONG_PASSWORD_FORMAT)
    private String password;

    @NotBlank
    @Pattern(
        regexp = PASSWORD_REGEXP,
        message = WRONG_PASSWORD_FORMAT)
    private String confirmPassword;

    @NotBlank
    private String token;
}