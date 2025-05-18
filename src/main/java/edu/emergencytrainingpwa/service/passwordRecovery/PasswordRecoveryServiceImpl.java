package edu.emergencytrainingpwa.service.passwordRecovery;

import edu.emergencytrainingpwa.constant.ErrorMessage;
import edu.emergencytrainingpwa.dao.entity.PasswordSecurity;
import edu.emergencytrainingpwa.dao.entity.RestorePasswordEmail;
import edu.emergencytrainingpwa.dao.entity.User;
import edu.emergencytrainingpwa.dao.repository.PasswordSecurityRepo;
import edu.emergencytrainingpwa.dao.repository.RestorePasswordEmailRepo;
import edu.emergencytrainingpwa.dao.repository.UserRepo;
import edu.emergencytrainingpwa.dto.passwordRecovery.RestoreDto;
import edu.emergencytrainingpwa.exception.BadRequestException;
import edu.emergencytrainingpwa.exception.NotFoundException;
import edu.emergencytrainingpwa.exception.UserActivationEmailTokenExpiredException;
import edu.emergencytrainingpwa.exception.WrongEmailException;
import edu.emergencytrainingpwa.security.jwt.JwtTool;
import edu.emergencytrainingpwa.service.email.EmailService;
import jakarta.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {
    private final UserRepo userRepo;
    private final RestorePasswordEmailRepo restorePasswordEmailRepo;
    private final PasswordSecurityRepo passwordSecurityRepo;
    private final JwtTool jwtTool;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    @Value("${verifyEmailTimeHour}")
    private Integer tokenExpirationTimeInHours;

    @Override
    public void sendPasswordRecoveryEmailTo(String email) {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_FOUND_BY_EMAIL + email));
        Optional<RestorePasswordEmail> existing = restorePasswordEmailRepo.findByUser(user);
        if (existing.isPresent()) {
            RestorePasswordEmail old = existing.get();
            if (old.getExpiryDate().isAfter(LocalDateTime.now())) {
                throw new WrongEmailException(ErrorMessage.PASSWORD_RESTORE_LINK_ALREADY_SENT + email);
            }
            restorePasswordEmailRepo.delete(old);
        }
        savePasswordRestorationTokenForUser(user, jwtTool.generateTokenKeyWithCodedDate());
    }

    @Override
    public void updatePasswordUsingToken(RestoreDto form) {
        RestorePasswordEmail tokenEntity = restorePasswordEmailRepo.findByToken(form.getToken())
            .orElseThrow(() -> new NotFoundException(ErrorMessage.LINK_IS_NO_ACTIVE));

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            throw new BadRequestException(ErrorMessage.PASSWORDS_DO_NOT_MATCH);
        }

        if (!isNotExpired(tokenEntity.getExpiryDate())) {
            log.info("Expired token for {}: {}", tokenEntity.getUser().getEmail(), form.getToken());
            throw new UserActivationEmailTokenExpiredException(ErrorMessage.LINK_IS_NO_ACTIVE);
        }

        updatePassword(form.getPassword(), tokenEntity.getUser().getId());

        emailService.sendSuccessRestorePasswordByEmail(
            tokenEntity.getUser().getEmail(),
            tokenEntity.getUser().getUsername()
        );

        restorePasswordEmailRepo.delete(tokenEntity);

        log.info("User {} reset password with token {}",
            tokenEntity.getUser().getEmail(), form.getToken());
    }

    private void savePasswordRestorationTokenForUser(User user, String token) {
        RestorePasswordEmail restorePasswordEmail =
            RestorePasswordEmail.builder()
                .user(user)
                .token(token)
                .expiryDate(calculateExpirationDate(tokenExpirationTimeInHours))
                .build();
        restorePasswordEmailRepo.save(restorePasswordEmail);
        emailService.sendRestoreEmail(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            token);
    }

    private boolean isNotExpired(LocalDateTime tokenExpirationDate) {
        return LocalDateTime.now().isBefore(tokenExpirationDate);
    }

    private LocalDateTime calculateExpirationDate(Integer expirationTimeInHours) {
        LocalDateTime now = LocalDateTime.now();
        return now.plusHours(expirationTimeInHours);
    }

    private void updatePassword(String pass, Long id) {
        String password = passwordEncoder.encode(pass);
        Optional<PasswordSecurity> passwordSecurity = passwordSecurityRepo.findByUserId(id);

        passwordSecurity.ifPresentOrElse(s -> {
            s.setPassword(password);
            passwordSecurityRepo.save(s);
        }, () -> passwordSecurityRepo.save(createPasswordSecurity(id, password)));
    }

    private PasswordSecurity createPasswordSecurity(Long id, String password) {
        return PasswordSecurity.builder()
            .password(password)
            .user(userRepo.findById(id).orElseThrow(NoResultException::new))
            .build();
    }
}
