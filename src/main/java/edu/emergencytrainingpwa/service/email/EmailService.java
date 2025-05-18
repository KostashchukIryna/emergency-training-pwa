package edu.emergencytrainingpwa.service.email;

public interface EmailService {

    void sendRestoreEmail(Long userId, String username, String userEmail, String token);

    void sendSuccessRestorePasswordByEmail(String email, String userName);
}
