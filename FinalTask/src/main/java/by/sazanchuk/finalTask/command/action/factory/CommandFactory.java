package by.sazanchuk.finalTask.command.action.factory;

import by.sazanchuk.finalTask.command.action.ChangeLanguageCommand;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.pet.DeletePetCommand;
import by.sazanchuk.finalTask.command.action.profile.EditProfileCommand;
import by.sazanchuk.finalTask.command.action.HomePageCommand;
import by.sazanchuk.finalTask.command.action.authorization.LoginCommand;
import by.sazanchuk.finalTask.command.action.authorization.LogoutCommand;
import by.sazanchuk.finalTask.command.action.profile.ProfileCommand;
import by.sazanchuk.finalTask.command.action.authorization.RegisterCommand;
import by.sazanchuk.finalTask.command.action.pet.RegisterPetCommand;

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
            case CHANGE_LANGUAGE: {
                commandResult = new ChangeLanguageCommand();
                break;
            }
            case DELETE_PET: {
                commandResult = new DeletePetCommand();
                break;
            }

        }
        return commandResult;
    }
}
