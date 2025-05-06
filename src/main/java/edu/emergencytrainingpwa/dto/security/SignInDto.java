package edu.emergencytrainingpwa.dto.security;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}