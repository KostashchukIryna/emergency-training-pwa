package edu.emergencytrainingpwa.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstant {
    public static final String ROLE = "role";
    public static final String ANONYMOUS = "anonymousUser";

    public static final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static final String USERNAME_REGEXP =
        "^[ґҐіІєЄїЇА-Яа-яa-zA-Z](?!.*\\.$)(?!.*?\\.\\.)(?!.*?--)(?!.*?'')[-'ʼ’ ґҐіІєЄїЇА-Яа-я\\w.]{0,29}$";
    public static final String USERNAME_MESSAGE= "Неправильний формат юзернейму";
    public static final String INVALID_EMAIL= "Неправильний формат email";
    public static final String PASSWORD_REGEXP= "^(?=.*\\d).{7,}$";
    public static final String WRONG_PASSWORD_FORMAT= "Неправильний формат паролю";
}
