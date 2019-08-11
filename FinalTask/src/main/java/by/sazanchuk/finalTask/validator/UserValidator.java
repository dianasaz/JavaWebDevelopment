package by.sazanchuk.finalTask.validator;

import by.sazanchuk.finalTask.entity.User;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator<User> {
    private static final String LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String PASSWORD = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private static final String EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String ADDRESS = "^[a-zA-Z]+\\s\\d{1,3}[\\.\\s\\,a-z]+[\\d]+";
    private static final String NAME = "^[A-Z][a-z]+\\s[A-Z][a-z]+$";
    private static final String PHONE = "[0-9]{9}";
    private static final String LOGIN_ERROR = "login_error";
    private static final String PASSWORD_ERROR = "password_error";
    private static final String ADDRESS_ERROR = "address_error";
    private static final String PHONE_ERROR = "phone_error";
    private static final String NAME_ERROR = "name_error";
    private static final String EMAIL_ERROR = "email_error";
    private static final String VALID = "valid";
    private static final String NULL_ENTITY = "null_entity";

    @Override
    public String isValid(User entity) {
        if (entity == null) return NULL_ENTITY;

        Pattern pattern = Pattern.compile(LOGIN);
        Matcher matcher = pattern.matcher(entity.getLogin());
        if (!matcher.find()) return LOGIN_ERROR;

        pattern = Pattern.compile(PASSWORD);
        matcher = pattern.matcher(entity.getPassword());
        if (!matcher.find()) return PASSWORD_ERROR;

        pattern = Pattern.compile(NAME);
        matcher = pattern.matcher(entity.getName());
        if (!matcher.find()) return NAME_ERROR;

        pattern = Pattern.compile(EMAIL);
        matcher = pattern.matcher(entity.getEmail());
        if (!matcher.find()) return EMAIL_ERROR;

        pattern = Pattern.compile(ADDRESS);
        matcher = pattern.matcher(entity.getAddress());
        if (!matcher.find()) return ADDRESS_ERROR;

        pattern = Pattern.compile(PHONE);
        matcher = pattern.matcher(String.valueOf(entity.getPhoneNumber()));
        if (!matcher.find()) return PHONE_ERROR;

        return VALID;
    }
}
