package by.sazanchuk.finalTask.command.action.factory;

public enum CommandType {
    LOGIN("login"),
    LOGOUT("logout"),
    HOME_PAGE("home_page"),
    REGISTER("register"),
    REGISTER_PET("register_pet"),
    PROFILE("profile");

    private String command;
    CommandType(String command) {
        this.command = command;
    }
}
