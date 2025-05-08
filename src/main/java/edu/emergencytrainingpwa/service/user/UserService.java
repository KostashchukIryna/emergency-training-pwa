package edu.emergencytrainingpwa.service.user;

import edu.emergencytrainingpwa.dto.user.UserDto;
import edu.emergencytrainingpwa.dto.user.UserRoleDto;
import edu.emergencytrainingpwa.dto.user.UserSecurityDto;
import edu.emergencytrainingpwa.enums.Role;
import java.util.Date;
import java.util.List;
import java.util.Optional;
//import org.springframework.data.domain.Pageable;

/*
  @author   kosta
  @project   emergency-training-pwa
  @class  UserService
  @version  1.0.0 
  @since 10.04.2025 - 00.06
*/
public interface UserService {
    void save(UserDto userDto);

    Long findIdByEmail(String email);

    /**
     * Method that allow you to find {@link UserDto} by id.
     *
     * @param id a value of {@link Long}
     * @return {@link UserDto} with this id.
     */
    UserDto findById(Long id);

    /**
     * Method that allow you to find {@link UserSecurityDto} by email.
     *
     * @param email a value of {@link String}
     * @return {@link UserDto} with this email.
     */
    UserSecurityDto findByEmail(String email);

    /**
     * Update {@code ROLE} of user.
     *
     * @param id   {@link UserDto} id.
     * @param role {@link Role} for user.
     * @return {@link UserRoleDto}
     * @deprecated updates like this on User entity should be handled in
     *             GreenCityUser via RestClient.
     */
    UserRoleDto updateRole(Long id, Role role, String email);

//    /**
//     * Method that returns a paginated list of users filtered by specified criteria.
//     *
//     * @param request  request for searching related data
//     * @param pageable pagination information including page number, size, and
//     *                 sorting options.
//     *
//     * @return a {@link PageableDetailedDto} containing a list of
//     *         {@link UserManagementVO} filtered by the given criteria, role, and
//     *         status, along with pagination details.
//     *
//     */
//    PageableDetailedDto<UserManagementVO> getAllUsersByCriteria(UserFilterDto request, Pageable pageable);

    /**
     * Find list of {@link UserDto}'s by emails.
     *
     * @param emails user emails.
     * @return list of {@link UserDto}.
     */
    List<UserDto> findByEmails(List<String> emails);
}
