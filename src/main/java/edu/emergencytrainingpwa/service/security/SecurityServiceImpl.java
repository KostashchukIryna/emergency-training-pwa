package edu.emergencytrainingpwa.service.security;

import edu.emergencytrainingpwa.constant.ErrorMessage;
import edu.emergencytrainingpwa.dao.entity.PasswordSecurity;
import edu.emergencytrainingpwa.dao.entity.User;
import edu.emergencytrainingpwa.dao.repository.UserRepo;
import edu.emergencytrainingpwa.dto.security.SignUpDto;
import edu.emergencytrainingpwa.dto.security.SuccessSignUpDto;
import edu.emergencytrainingpwa.enums.Role;
import edu.emergencytrainingpwa.exception.UserAlreadyRegisteredException;
import edu.emergencytrainingpwa.security.jwt.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final UserRepo userRepo;
    private final JwtTool jwtTool;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public SuccessSignUpDto signUp(SignUpDto dto) {
        User user = createNewRegisteredUser(dto, jwtTool.generateTokenKey());
        user.setPasswordSecurity(createOwnSecurity(dto, user));
        try {
            User savedUser = userRepo.save(user);
            user.setId(savedUser.getId());
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyRegisteredException(ErrorMessage.USER_EXISTS_WITH_THIS_EMAIL);
        }
        return new SuccessSignUpDto(user.getId(), user.getUsername(), user.getEmail());
    }

    private User createNewRegisteredUser(SignUpDto dto, String refreshTokenKey) {
        return User.builder()
            .username(dto.getUsername())
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .patronymicName(dto.getPatronymicName())
            .email(dto.getEmail())
            .role(Role.ROLE_USER)
            .refreshTokenKey(refreshTokenKey)
            .build();
    }

    private PasswordSecurity createOwnSecurity(SignUpDto dto, User user) {
        return PasswordSecurity.builder()
            .password(passwordEncoder.encode(dto.getPassword()))
            .user(user)
            .build();
    }
}
