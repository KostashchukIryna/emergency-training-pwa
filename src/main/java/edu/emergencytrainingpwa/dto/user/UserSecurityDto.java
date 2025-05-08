package edu.emergencytrainingpwa.dto.user;

import edu.emergencytrainingpwa.dao.entity.PasswordSecurity;
import edu.emergencytrainingpwa.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserSecurityDto {
    private Long id;

    private Role role;

    private String refreshTokenKey;

    private PasswordSecurity passwordSecurity;
}
