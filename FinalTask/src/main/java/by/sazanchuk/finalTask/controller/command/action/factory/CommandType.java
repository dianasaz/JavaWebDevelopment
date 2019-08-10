package by.sazanchuk.finalTask.controller.command.action.factory;

import java.util.stream.Stream;

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
    WATCH_DOCTOR("watch_doctor"),
    DELETE_DOCTOR("delete_doctor"),
    TAKE_COUPON("take_coupon"),
    EDIT_DOCTOR("edit_doctor"),
    EDIT_SERVICE("edit_service");


    private String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType of(String command) {

        return Stream.of(CommandType.values())
                .filter(c -> c.command.equalsIgnoreCase(command))
                .findFirst().orElse(HOME_PAGE);
    }
}
