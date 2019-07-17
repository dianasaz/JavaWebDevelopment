package action;

public enum CommandType {
    LOGIN("login"),
    LOGOUT("logout"),
    MAIN("main");

    private String command;
    private CommandType(String command) {
        this.command = command;
    }
}
