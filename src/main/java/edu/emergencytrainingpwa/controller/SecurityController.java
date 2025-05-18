package edu.emergencytrainingpwa.controller;

import edu.emergencytrainingpwa.constant.AppConstant;
import edu.emergencytrainingpwa.dto.passwordRecovery.RestoreDto;
import edu.emergencytrainingpwa.dto.security.SignInDto;
import edu.emergencytrainingpwa.dto.security.SignUpDto;
import edu.emergencytrainingpwa.dto.security.SuccessSignInDto;
import edu.emergencytrainingpwa.dto.security.SuccessSignUpDto;
import edu.emergencytrainingpwa.service.passwordRecovery.PasswordRecoveryService;
import edu.emergencytrainingpwa.service.security.SecurityService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import java.util.Locale;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/security")
@Validated
@Slf4j
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;
    private final PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/signUp")
    public ResponseEntity<SuccessSignUpDto> singUp(@Valid @RequestBody SignUpDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(securityService.signUp(dto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<SuccessSignInDto> signIn(@Valid @RequestBody SignInDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(securityService.signIn(dto));
    }

    @GetMapping("/forgotPassword")
    public ResponseEntity<Object> restorePassword(@RequestParam @Email(regexp = AppConstant.EMAIL_REGEXP,
        message = AppConstant.INVALID_EMAIL) String email) {
        passwordRecoveryService.sendPasswordRecoveryEmailTo(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody RestoreDto form) {
        passwordRecoveryService.updatePasswordUsingToken(form);
        return ResponseEntity.ok().build();
    }
}
