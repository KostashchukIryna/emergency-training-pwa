package edu.emergencytrainingpwa.dto.user;

import edu.emergencytrainingpwa.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  @author   kosta
  @project   emergency-training-pwa
  @class  UserDto
  @version  1.0.0 
  @since 10.04.2025 - 00.13
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String patronymicName;

    private String email;

    private Role role;
}
