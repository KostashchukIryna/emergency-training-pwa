package edu.emergencytrainingpwa.dao.entity;

import edu.emergencytrainingpwa.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "patronymic_name", length = 30)
    private String patronymicName;

    @Column(nullable = false, length = 40)
    private String username;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    private PasswordSecurity passwordSecurity;

    @Column(name = "refresh_token_key", nullable = false)
    private String refreshTokenKey;
}
