package edu.emergencytrainingpwa.security.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static edu.emergencytrainingpwa.constant.AppConstant.ANONYMOUS;

/*
  @author   kosta
  @project   emergency-training-pwa
  @class  SecurityUtils
  @version  1.0.0 
  @since 07.04.2025 - 22.58
*/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            return principal != null && !ANONYMOUS.equals(principal.toString());
        }
        return false;
    }
}
