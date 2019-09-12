package by.sazanchuk.finalTask.validator;

import by.sazanchuk.finalTask.entity.Doctor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Doctor validator.
 */
public class DoctorValidator implements Validator<Doctor> {
    private static final String NAME = "^[A-Z][a-z]+\\s[A-Z][a-z]+(\\s[A-Z][a-z]+)?$";
    private static final String ENTITY_NULL = "entity_null";
    private static final String NAME_ERROR = "name_error";
    private static final String VALID = "valid";

    private static DoctorValidator validator;

    private DoctorValidator(){
        validator = new DoctorValidator();
    }

    @Override
    public String isValid(Doctor entity) {
        if (entity == null) return ENTITY_NULL;

        Pattern pattern = Pattern.compile(NAME);
        Matcher matcher = pattern.matcher(entity.getName());
        if (!matcher.find()) return NAME_ERROR;

        return VALID;
    }

    public static DoctorValidator getValidator() {
        if (validator == null) return new DoctorValidator();
        else return validator;
    }
}
