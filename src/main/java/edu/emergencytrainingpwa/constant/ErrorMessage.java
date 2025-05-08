package edu.emergencytrainingpwa.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {
    public static final String USER_EXISTS_WITH_THIS_EMAIL = "User with this email already exists";
    public static final String USER_NOT_FOUND_BY_EMAIL = "User with this email does not exist";
    public static final String BAD_PASSWORD = "Bad password";
    public static final String CATEGORY_NOT_FOUND = "Category not found: ";
    public static final String TAGS_NOT_FOUND = "One or more tags not found: ";
}
