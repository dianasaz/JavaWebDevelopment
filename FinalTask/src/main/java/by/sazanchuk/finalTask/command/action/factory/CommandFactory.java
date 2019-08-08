package by.sazanchuk.finalTask.command.action.factory;

import by.sazanchuk.finalTask.command.action.ChangeLanguageCommand;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.coupon.TakeCouponCommand;
import by.sazanchuk.finalTask.command.action.doctor.AddDoctorCommand;
import by.sazanchuk.finalTask.command.action.doctor.DeleteDoctorCommand;
import by.sazanchuk.finalTask.command.action.doctor.WatchDoctorCommand;
import by.sazanchuk.finalTask.command.action.pet.DeletePetCommand;
import by.sazanchuk.finalTask.command.action.profile.ProfileAdminCommand;
import by.sazanchuk.finalTask.command.action.profile.ProfileCommand;
import by.sazanchuk.finalTask.command.action.profileUser.EditProfileCommand;
import by.sazanchuk.finalTask.command.action.HomePageCommand;
import by.sazanchuk.finalTask.command.action.authorization.LoginCommand;
import by.sazanchuk.finalTask.command.action.authorization.LogoutCommand;
import by.sazanchuk.finalTask.command.action.profileUser.ProfileUserCommand;
import by.sazanchuk.finalTask.command.action.authorization.RegisterCommand;
import by.sazanchuk.finalTask.command.action.pet.RegisterPetCommand;
import by.sazanchuk.finalTask.command.action.service.AddServiceCommand;
import by.sazanchuk.finalTask.command.action.service.DeleteServiceCommand;
import by.sazanchuk.finalTask.command.action.service.WatchServiceCommand;

import java.util.EnumMap;
import java.util.Map;

import static by.sazanchuk.finalTask.command.action.factory.CommandType.ADD_DOCTOR;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.ADD_SERVICE;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.CHANGE_LANGUAGE;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.DELETE_DOCTOR;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.DELETE_PET;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.DELETE_SERVICE;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.EDIT_PROFILE;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.HOME_PAGE;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.LOGIN;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.LOGOUT;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.PROFILE;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.PROFILE_ADMIN;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.PROFILE_USER;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.REGISTER;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.REGISTER_PET;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.TAKE_COUPON;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.WATCH_DOCTOR;
import static by.sazanchuk.finalTask.command.action.factory.CommandType.WATCH_SERVICE;

public class CommandFactory {

    private static Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);
    static {
        commands.put(LOGIN, new LoginCommand());
        commands.put(HOME_PAGE, new HomePageCommand());
        commands.put(REGISTER, new RegisterCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(PROFILE, new ProfileCommand());
        commands.put(REGISTER_PET, new RegisterPetCommand());
        commands.put(EDIT_PROFILE, new EditProfileCommand());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(DELETE_PET, new DeletePetCommand());
        commands.put(WATCH_SERVICE, new WatchServiceCommand());
        commands.put(ADD_SERVICE, new AddServiceCommand());
        commands.put(PROFILE_USER, new ProfileUserCommand());
        commands.put(PROFILE_ADMIN, new ProfileAdminCommand());
        commands.put(DELETE_SERVICE, new DeleteServiceCommand());
        commands.put(ADD_DOCTOR, new AddDoctorCommand());
        commands.put(WATCH_DOCTOR, new WatchDoctorCommand());
        commands.put(DELETE_DOCTOR, new DeleteDoctorCommand());
        commands.put(TAKE_COUPON, new TakeCouponCommand());
    }

    public static Command create(String command) {
       return commands.get(CommandType.of(command));
    }
}
