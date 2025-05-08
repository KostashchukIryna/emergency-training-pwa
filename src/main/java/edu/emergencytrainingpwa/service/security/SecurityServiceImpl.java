package edu.emergencytrainingpwa.service.security;

import edu.emergencytrainingpwa.constant.ErrorMessage;
import edu.emergencytrainingpwa.dao.entity.PasswordSecurity;
import edu.emergencytrainingpwa.dao.entity.User;
import edu.emergencytrainingpwa.dao.repository.UserRepo;
import edu.emergencytrainingpwa.dto.security.SignInDto;
import edu.emergencytrainingpwa.dto.security.SignUpDto;
import edu.emergencytrainingpwa.dto.security.SuccessSignInDto;
import edu.emergencytrainingpwa.dto.security.SuccessSignUpDto;
import edu.emergencytrainingpwa.dto.user.UserSecurityDto;
import edu.emergencytrainingpwa.enums.Role;
import edu.emergencytrainingpwa.exception.NoSuchUserException;
import edu.emergencytrainingpwa.exception.UserAlreadyRegisteredException;
import edu.emergencytrainingpwa.exception.WrongPasswordException;
import edu.emergencytrainingpwa.security.jwt.JwtTool;
import edu.emergencytrainingpwa.service.user.UserService;
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
    private final UserService userService;
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

    @Override
    public SuccessSignInDto signIn(SignInDto dto) {
        String email = dto.getEmail();
        UserSecurityDto user = validateUser(email);

        validatePassword(dto, user);

        return createSuccessSignInResponse(user, email);
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

    private UserSecurityDto validateUser(final String email) {
        UserSecurityDto user = userService.findByEmail(email);
        if (user == null) {
            throw new NoSuchUserException(ErrorMessage.USER_NOT_FOUND_BY_EMAIL + email);
        }
        return user;
    }

    private void validatePassword(final SignInDto dto, UserSecurityDto user) {
        if (!isPasswordCorrect(dto, user)) {
            throw new WrongPasswordException(ErrorMessage.BAD_PASSWORD);
        }
    }

    private boolean isPasswordCorrect(SignInDto signInDto, UserSecurityDto user) {
        if (user.getPasswordSecurity() == null) {
            return false;
        }
        return passwordEncoder.matches(signInDto.getPassword(), user.getPasswordSecurity().getPassword());
    }

    private SuccessSignInDto createSuccessSignInResponse(UserSecurityDto user, String email) {
        String accessToken = jwtTool.createAccessToken(email, user.getRole());
        String refreshToken = jwtTool.createRefreshToken(user, email);
        return new SuccessSignInDto(user.getId(), accessToken, refreshToken, email);
    }
}
