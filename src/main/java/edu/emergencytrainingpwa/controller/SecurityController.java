package edu.emergencytrainingpwa.controller;

import edu.emergencytrainingpwa.dto.security.SignInDto;
import edu.emergencytrainingpwa.dto.security.SignUpDto;
import edu.emergencytrainingpwa.dto.security.SuccessSignInDto;
import edu.emergencytrainingpwa.dto.security.SuccessSignUpDto;
import edu.emergencytrainingpwa.service.security.SecurityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/security")
@Validated
@Slf4j
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping("/signUp")
    public ResponseEntity<SuccessSignUpDto> singUp(@Valid @RequestBody SignUpDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(securityService.signUp(dto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<SuccessSignInDto> signIn(@Valid @RequestBody SignInDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(securityService.signIn(dto));
    }

}
