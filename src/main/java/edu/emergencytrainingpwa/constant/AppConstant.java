package edu.emergencytrainingpwa.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstant {
    public static final String ROLE = "role";
    public static final String ANONYMOUS = "anonymousUser";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static final String USERNAME_REGEXP =
        "^[ґҐіІєЄїЇА-Яа-яa-zA-Z](?!.*\\.$)(?!.*?\\.\\.)(?!.*?--)(?!.*?'')[-'ʼ’ ґҐіІєЄїЇА-Яа-я\\w.]{0,29}$";
    public static final String USERNAME_MESSAGE= "Неправильний формат юзернейму";
    public static final String INVALID_EMAIL= "Неправильний формат email";
    public static final String PASSWORD_REGEXP= "^(?=.*\\d).{7,}$";
    public static final String WRONG_PASSWORD_FORMAT= "Неправильний формат паролю";
    public static final String NAME_REGEXP = "^[А-ЯҐЄІЇ][а-яґєії]+(?:-[А-ЯҐЄІЇ][а-яґєії]+)*$";
    public static final String WRONG_FIRST_NAME_FORMAT= "Неправильний формат імені";
    public static final String WRONG_LAST_NAME_FORMAT= "Неправильний формат прізвища";
    public static final String WRONG_PATRONYMIC_NAME_FORMAT= "Неправильний формат по-батькові";

    public static final String IN_CREATE_TEMPLATE_NAME = "in createEmailTemplate(), vars: {}, templateName: {}";
    public static final String IN_SEND_EMAIL = "in sendEmail(), receiver: {}, subject: {}";
    public static final String CLIENT_LINK = "clientLink";
    public static final String USER_NAME = "name";
    public static final String RESTORE_PASS = "restorePassword";
    public static final String PARAM_USER_ID = "&user_id=";
    public static final String RESTORE_EMAIL_PAGE = "restore-email-page";
    public static final String CONFIRM_RESTORING_PASS = "Підтвердження відновлення паролю";
    public static final String RESTORE_PASSWORD = "Відновлення пароль";
    public static final String SUCCESS_RESTORED_PASSWORD_PAGE = "success-restored-password-page";
}
