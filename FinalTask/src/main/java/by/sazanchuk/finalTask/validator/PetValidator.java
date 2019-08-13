package by.sazanchuk.finalTask.validator;

import by.sazanchuk.finalTask.entity.Pet;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Pet validator.
 */
public class PetValidator implements Validator<Pet> {
    private static final String NAME = "^[A-Z][a-z]{1,16}$";
    private static final String ENTITY_NULL = "entity_null";
    private static final String DATE_ERROR= "date_error";
    private static final String NAME_ERROR= "name_error";
    private static final String VALID = "valid";

    @Override
    public String isValid(Pet entity) {
        if (entity == null) return ENTITY_NULL;

        if (entity.getDateOfBirth().after(new Date())) {
            return DATE_ERROR;
        }

        Pattern pattern = Pattern.compile(NAME);
        Matcher matcher = pattern.matcher(entity.getName());
        if (!matcher.find()) return NAME_ERROR;

        return VALID;
    }
}
