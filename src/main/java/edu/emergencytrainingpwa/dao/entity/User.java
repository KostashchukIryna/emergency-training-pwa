package edu.emergencytrainingpwa.dao.entity;

import edu.emergencytrainingpwa.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "first_name",nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(nullable = false, length = 40)
    private String username;

    @Column(name = "patronymic_name", nullable = false, length = 30)
    private String patronymicName;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
