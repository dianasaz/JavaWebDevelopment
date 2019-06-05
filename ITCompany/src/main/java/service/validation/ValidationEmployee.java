package service.validation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ValidationEmployee implements Validation {

    private final Logger logger = LogManager.getLogger(ValidationEmployee.class);

    private static final String regex = "^([A-Z])+\\,\\s\\W*\\w{1,15}\\s*\\W*\\w{0,15}\\,\\s([A-Z])+\\,\\s([A-Z])+\\,\\s[1-3]000$";

    @Override
    public boolean isValid(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).matches(regex)) {
                logger.log(Level.ERROR, "Line " + list.indexOf(list.get(i)) + "is invalid. Emergency stop!");
                return false;
            }
            logger.log(Level.INFO, "Line " + list.indexOf(list.get(i)) + "is valid");
        }
        return true;
    }
}
