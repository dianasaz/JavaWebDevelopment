package entity;

import exceptions.InvalidDataException;

import java.lang.invoke.SwitchPoint;

public enum MainSkill {
    JAVA,
    PYTHON,
    PHP,
    JAVASCRIPT,
    SWIFT;

    public static MainSkill setMainSkill(String string) throws InvalidDataException {
        MainSkill mainSkill = null;
        switch (string) {
            case "JAVA": {
                mainSkill = JAVA;
                break;
            }
            case "PYTHON": {
                mainSkill = PYTHON;
                break;
            }
            case "JAVASCRIPT": {
                mainSkill = JAVASCRIPT;
                break;
            }
            case "PHP": {
                mainSkill = PHP;
                break;
            }
            case "SWIFT": {
                mainSkill = SWIFT;
                break;
            }
            default: {
                throw new InvalidDataException("invalid information");
            }
        }
        return mainSkill;
    }
}
