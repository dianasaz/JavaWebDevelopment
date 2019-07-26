package by.sazanchuk.finalTask.command.action.factory;

import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.EditProfileCommand;
import by.sazanchuk.finalTask.command.action.HomePageCommand;
import by.sazanchuk.finalTask.command.action.LoginCommand;
import by.sazanchuk.finalTask.command.action.LogoutCommand;
import by.sazanchuk.finalTask.command.action.ProfileCommand;
import by.sazanchuk.finalTask.command.action.RegisterCommand;
import by.sazanchuk.finalTask.command.action.RegisterPetCommand;

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
            case LOGOUT: {
                commandResult = new LogoutCommand();
                break;
            }
            case PROFILE: {
                commandResult = new ProfileCommand();
                break;
            }
            case REGISTER_PET: {
                commandResult = new RegisterPetCommand();
                break;
            }
            case EDIT_PROFILE: {
                commandResult = new EditProfileCommand();
                break;
            }
        }
        return commandResult;
    }
}
