package by.sazanchuk.finalTask.action.command.factory;

public enum CommandType {
    LOGIN("login"),
    LOGOUT("logout"),
    HOME_PAGE("home_page"),
    REGISTER("register");

    private String command;
    CommandType(String command) {
        this.command = command;
    }
}
