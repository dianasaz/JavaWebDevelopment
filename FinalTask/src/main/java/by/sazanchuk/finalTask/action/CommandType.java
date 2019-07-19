package by.sazanchuk.finalTask.action;

public enum CommandType {
    LOGIN("login"),
    LOGOUT("logout"),
    MAIN("main"),
    REGISTER("register");

    private String command;
    private CommandType(String command) {
        this.command = command;
    }
}
