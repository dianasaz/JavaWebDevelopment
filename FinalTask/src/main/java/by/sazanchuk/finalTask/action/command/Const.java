package by.sazanchuk.finalTask.action.command;

public enum Const {

    ID ("id"),

    ROLE ("role"),

    USER ("user"),

    INCLUDE("includeView"),

    USER_INFO("userInfo");

    private final String fieldName;

    private Const(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}

