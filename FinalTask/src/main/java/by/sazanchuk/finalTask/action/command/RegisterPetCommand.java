package by.sazanchuk.finalTask.action.command;

import by.sazanchuk.finalTask.action.ConfigurationManager;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.PetList;
import by.sazanchuk.finalTask.service.PetService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class RegisterPetCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);

    private static final String NAME = "name";
    private static final String USER_ID = "user_id";
    private static final String KIND = "kind";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String ERROR_REGISTRATION = "error_registration";
    private static final String ERROR = "error_";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String name = request.getParameter(NAME);
        String kind = request.getParameter(KIND);
       // parameters.put(DATE_OF_BIRTH, request.getParameter(DATE_OF_BIRTH));


        try {
            createPet(name, kind, request);
            //logger.log(Level.INFO, "user registrated and authorized with login - " + parameters.get(LOGIN));
            return new CommandResult("controller?command=profile", true);
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }

    }


    private void createPet(String name, String kind, HttpServletRequest request) throws DaoException, ServiceException, ConnectionPoolException {
        Integer user_id = (Integer) request.getSession().getAttribute(USER_ID);
        if (user_id != null) {
            Pet pet = new Pet();
            pet.setName(name);
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            try {
                pet.setDateOfBirth(dateFormat.parse("2000-11-11"));
            } catch (ParseException e) {

            }
            pet.setKind(PetList.setPet(kind));
            pet.setUser_identity(user_id);


            ServiceFactory factory = new ServiceFactory();
            PetService service = factory.getService(PetService.class);

            int id = service.save(pet);
            if (id != 0) {
                pet.setIdentity(id);
            } else {
                throw new ServiceException("Can't save pet!");
            }
            setAtributesToSession(pet, request);
        }
    }

    private void setAtributesToSession(Pet pet, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("pet", pet);
        request.setAttribute("petName", pet.getName());
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.profile"), false);
    }
}