package by.sazanchuk.finalTask.command;

public enum Page {
    HOME_PAGE("/index.jsp"),
    LOGIN("/login.jsp"),
    PROFILE_USER("/profileUser.jsp"),
    PROFILE_ADMIN("/profileAdmin.jsp"),
    REGISTER_PET("/register_pet.jsp"),
    EDIT_PROFILE("/edit_profile.jsp"),
    REGISTER("/register.jsp"),
    NO_ACCESS("/noAccessPage.jsp"),
    WATCH_SERVICE("/watchService.jsp");


    private final String value;

    Page(String value) {
        this.value = value;
    }

    public String getPage() {
        return value;
    }
}
