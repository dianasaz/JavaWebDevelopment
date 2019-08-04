package by.sazanchuk.finalTask.command.action.factory;

public enum CommandType {
    LOGIN("login"),
    LOGOUT("logout"),
    HOME_PAGE("home_page"),
    REGISTER("register"),
    REGISTER_PET("register_pet"),
    PROFILE("profile"),
    EDIT_PROFILE("edit_profile"),
    CHANGE_LANGUAGE("change_language"),
    DELETE_PET("delete_pet"),
    WATCH_SERVICE("watch_service"),
    PROFILE_USER("profile_user"),
    PROFILE_ADMIN("profile_admin"),
    ADD_SERVICE("add_service"),
    DELETE_SERVICE("delete_service"),
    ADD_DOCTOR("add_doctor"),
    WATCH_DOCTOR("watch_doctor");



    private String command;
    CommandType(String command) {
        this.command = command;
    }
}
