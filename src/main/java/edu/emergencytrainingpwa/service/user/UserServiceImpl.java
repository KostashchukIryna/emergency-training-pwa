package edu.emergencytrainingpwa.service.user;

import edu.emergencytrainingpwa.dto.user.UserDto;
import edu.emergencytrainingpwa.dto.user.UserRoleDto;
import edu.emergencytrainingpwa.enums.Role;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    /**
     * @param userDto
     */
    @Override
    public void save(UserDto userDto) {

    }

    /**
     * @param email
     * @return
     */
    @Override
    public Long findIdByEmail(String email) {
        return 0L;
    }

    /**
     * Method that allow you to find {@link UserDto} by id.
     *
     * @param id a value of {@link Long}
     * @return {@link UserDto} with this id.
     */
    @Override
    public UserDto findById(Long id) {
        return null;
    }

    /**
     * Method that allow you to find {@link UserDto} by email.
     *
     * @param email a value of {@link String}
     * @return {@link UserDto} with this email.
     */
    @Override
    public Optional<UserDto> findByEmail(String email) {
        return Optional.empty();
    }

    /**
     * Update {@code ROLE} of user.
     *
     * @param id    {@link UserDto} id.
     * @param role  {@link Role} for user.
     * @param email
     * @return {@link UserRoleDto}
     * @deprecated updates like this on User entity should be handled in
     * GreenCityUser via RestClient.
     */
    @Override
    public UserRoleDto updateRole(Long id, Role role, String email) {
        return null;
    }

    /**
     * Find list of {@link UserDto}'s by emails.
     *
     * @param emails user emails.
     * @return list of {@link UserDto}.
     */
    @Override
    public List<UserDto> findByEmails(List<String> emails) {
        return List.of();
    }
}
