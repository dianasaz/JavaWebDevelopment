package by.sazanchuk.finalTask.action;

public enum Page {
    HOME_PAGE("/index.jsp"),

    LOGIN("/login.jsp"),

    REGISTER("/register.jsp");

    private final String value;

    Page(String value) {
        this.value = value;
    }

    public String getPage() {
        return value;
    }
}