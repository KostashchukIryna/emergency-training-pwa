package edu.emergencytrainingpwa.service.security;

import edu.emergencytrainingpwa.dto.security.SignInDto;
import edu.emergencytrainingpwa.dto.security.SignUpDto;
import edu.emergencytrainingpwa.dto.security.SuccessSignInDto;
import edu.emergencytrainingpwa.dto.security.SuccessSignUpDto;

public interface SecurityService {
    SuccessSignUpDto signUp(SignUpDto dto);

    SuccessSignInDto signIn(SignInDto dto);
//
//    AccessRefreshTokensDto updateAccessTokens(String refreshToken);
//
//    void updateCurrentPassword(UpdatePasswordDto updatePasswordDto, String email);
//
//    boolean hasPassword(String email);
//
//    void setPassword(SetPasswordDto dto, String email);
//
//    void deleteUserByEmail(String email);
//
//    void unblockAccount(String token);
}
