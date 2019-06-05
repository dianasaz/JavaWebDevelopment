package service.validation;

public class ValidationFactory {
    public static Validation getValidation(Type validationType){
        Validation validation = null;
        switch (validationType){
            case VALIDATION_EMPLOYEE:
                validation = new ValidationEmployee();
                break;
        }
        return validation;
    }
}

