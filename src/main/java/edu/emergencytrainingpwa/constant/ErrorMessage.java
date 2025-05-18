package edu.emergencytrainingpwa.constant;


import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {
    public static final String USER_EXISTS_WITH_THIS_EMAIL = "User with this email already exists";
    public static final String USER_NOT_FOUND_BY_EMAIL = "User with this email does not exist";
    public static final String BAD_PASSWORD = "Bad password";
    public static final String CATEGORY_NOT_FOUND = "Category not found: ";
    public static final String TAGS_NOT_FOUND = "One or more tags not found: ";
    public static final String TAG_NOT_FOUND = "Tag not found: ";
    public static final String PAGE_NOT_FOUND_MESSAGE = "Requested page is not found";
    public static final String COURSE_NOT_FOUND = "Course not found: ";

    public static final String PASSWORD_RESTORE_LINK_ALREADY_SENT = "Password restore link already sent to: ";
    public static final String LINK_IS_NO_ACTIVE = "Link is not active";
    public static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match";
}
