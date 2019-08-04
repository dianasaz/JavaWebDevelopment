package by.sazanchuk.finalTask.command;

public enum Page {
    HOME_PAGE("/index.jsp"),
    LOGIN("/login.jsp"),
    PROFILE_USER("/profile_user.jsp"),
    PROFILE_ADMIN("/profile_admin.jsp"),
    REGISTER_PET("/register_pet.jsp"),
    EDIT_PROFILE("/edit_profile.jsp"),
    REGISTER("/register.jsp"),
    NO_ACCESS("/noAccessPage.jsp"),
    WATCH_SERVICE("/watch_service.jsp"),
    ADD_SERVICE("/add_service.jsp"),
    ADD_DOCTOR("/add_doctor.jsp"),
    WATCH_DOCTOR("/watch_doctor.jsp");


    private final String value;

    Page(String value) {
        this.value = value;
    }

    public String getPage() {
        return value;
    }
}
