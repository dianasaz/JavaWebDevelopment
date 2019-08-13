package by.sazanchuk.finalTask.controller.command;

public enum Page {
    HOME_PAGE("/WEB-INF/main//index.jsp"),
    LOGIN("/WEB-INF/access/login.jsp"),
    PROFILE_USER("/WEB-INF/profile/profile_user.jsp"),
    PROFILE_ADMIN("/WEB-INF/profile/profile_admin.jsp"),
    REGISTER_PET("/WEB-INF/register/register_pet.jsp"),
    EDIT_PROFILE("/WEB-INF/edit/edit_profile.jsp"),
    REGISTER("/WEB-INF/register/register.jsp"),
    NO_ACCESS("/WEB-INF/access/noAccessPage.jsp"),
    WATCH_SERVICE("/WEB-INF/watch/watch_service.jsp"),
    ADD_SERVICE("/WEB-INF/add/add_service.jsp"),
    ADD_DOCTOR("/WEB-INF/add/add_doctor.jsp"),
    WATCH_DOCTOR("/WEB-INF/watch/watch_doctor.jsp"),
    TAKE_COUPON("/WEB-INF/add/take_coupon.jsp");


    private final String value;

    Page(String value) {
        this.value = value;
    }

    public String getPage() {
        return value;
    }
}
