package by.sazanchuk.finalTask.action.command.factory;

import by.sazanchuk.finalTask.action.command.Command;
import by.sazanchuk.finalTask.action.command.HomePageCommand;
import by.sazanchuk.finalTask.action.command.LoginCommand;
import by.sazanchuk.finalTask.action.command.RegisterCommand;

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
            case HOME_PAGE: {
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
