package by.sazanchuk.finalTask.action;

public class CommandFactory {
    public static Command create(String command) {
        command = command.toUpperCase();

        CommandType commandType = CommandType.valueOf(command);
        Command commandResult = null;

        switch (commandType) {
            case LOGIN: {
                commandResult = new LoginCommand();
                break;
            }
            case MAIN: {
                commandResult = new HomePageCommand();
                break;
            }
            case REGISTER: {
                commandResult = new RegisterCommand();
                break;
            }
        }
        return commandResult;
    }
}
