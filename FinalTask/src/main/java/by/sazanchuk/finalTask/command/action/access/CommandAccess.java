package by.sazanchuk.finalTask.command.action.access;

import by.sazanchuk.finalTask.command.action.factory.CommandType;

import java.util.ArrayList;
import java.util.List;

public class CommandAccess {


    public List<CommandType> getAvailableCommandTypesByUser(String role) {
        List<CommandType> listAvailableCommands = new ArrayList<>();
        listAvailableCommands.addAll(getCommandsForNotAuthorizedUsers());
        if(role != null) {
            listAvailableCommands.addAll(getCommonCommands());
            switch (role) {
                case "visitor": {
                    listAvailableCommands.addAll(getVisitorCommands());
                    break;
                }
                case "administrator": {
                    listAvailableCommands.addAll(getAdminCommands());
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unsupported role");
                    //listAvailableCommands.addAll(getVisitorCommands());
                }
            }
        }
        return listAvailableCommands;
    }

    private List<CommandType> getVisitorCommands() {
        List<CommandType> commandTypes = new ArrayList<>();
        commandTypes.add(CommandType.EDIT_PROFILE);
        commandTypes.add(CommandType.DELETE_PET);
        commandTypes.add(CommandType.REGISTER_PET);
        return commandTypes;
    }

    private List<CommandType> getCommonCommands() {
        List<CommandType> commandTypes = new ArrayList<>();
        commandTypes.add(CommandType.CHANGE_LANGUAGE);
        commandTypes.add(CommandType.REGISTER);
        commandTypes.add(CommandType.LOGIN);
        commandTypes.add(CommandType.HOME_PAGE);
        commandTypes.add(CommandType.LOGOUT);
        return commandTypes;
    }


    private List<CommandType> getAdminCommands() {
        return new ArrayList<>();
    }

    private List<CommandType> getCommandsForNotAuthorizedUsers() {
        List<CommandType> commandTypes = new ArrayList<>();
        commandTypes.add(CommandType.LOGIN);
        commandTypes.add(CommandType.CHANGE_LANGUAGE);
        commandTypes.add(CommandType.REGISTER);
        commandTypes.add(CommandType.WATCH_SERVICE);
        commandTypes.add(CommandType.HOME_PAGE);
        commandTypes.add(CommandType.PROFILE);


        return commandTypes;
    }

}
