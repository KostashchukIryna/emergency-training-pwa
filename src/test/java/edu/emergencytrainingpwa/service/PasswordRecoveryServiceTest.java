package edu.emergencytrainingpwa.service;

import edu.emergencytrainingpwa.dao.entity.User;
import edu.emergencytrainingpwa.dao.repository.PasswordSecurityRepo;
import edu.emergencytrainingpwa.dao.repository.RestorePasswordEmailRepo;
import edu.emergencytrainingpwa.dao.repository.UserRepo;
import edu.emergencytrainingpwa.security.jwt.JwtTool;
import edu.emergencytrainingpwa.service.email.EmailService;
import edu.emergencytrainingpwa.service.passwordRecovery.PasswordRecoveryServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PasswordRecoveryServiceTest {
    @Mock
    UserRepo userRepo;
    @Mock
    RestorePasswordEmailRepo tokenRepo;
    @Mock
    PasswordSecurityRepo pwSecRepo;
    @Mock
    JwtTool jwtTool;
    @Mock
    EmailService emailService;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    PasswordRecoveryServiceImpl svc;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
            svc,
            "tokenExpirationTimeInHours",
            2
        );
    }

    @Test
    void sendPasswordRecovery_createsNewToken_whenNoneExists() {
        User u = new User(); u.setId(42L); u.setEmail("a@b.c"); u.setUsername("alice");
        when(userRepo.findByEmail("a@b.c")).thenReturn(Optional.of(u));
        when(jwtTool.generateTokenKeyWithCodedDate()).thenReturn("tok-123");

        svc.sendPasswordRecoveryEmailTo("a@b.c");

        verify(tokenRepo).save(argThat(r ->
            r.getUser().equals(u) &&
                r.getToken().equals("tok-123")
        ));
        verify(emailService).sendRestoreEmail(eq(42L), eq("alice"), eq("a@b.c"), eq("tok-123"));
    }
}
