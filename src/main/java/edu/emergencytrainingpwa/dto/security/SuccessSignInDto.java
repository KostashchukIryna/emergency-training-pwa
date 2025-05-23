package edu.emergencytrainingpwa.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuccessSignInDto {
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private String email;
}
