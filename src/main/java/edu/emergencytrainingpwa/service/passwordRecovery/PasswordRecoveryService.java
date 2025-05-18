package edu.emergencytrainingpwa.service.passwordRecovery;

import edu.emergencytrainingpwa.dto.passwordRecovery.RestoreDto;

public interface PasswordRecoveryService {
    void sendPasswordRecoveryEmailTo(String email);

    void updatePasswordUsingToken(RestoreDto form);

}
