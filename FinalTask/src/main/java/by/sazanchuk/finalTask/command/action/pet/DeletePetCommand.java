package by.sazanchuk.finalTask.command.action.pet;

import by.sazanchuk.finalTask.command.ConfigurationManager;
import by.sazanchuk.finalTask.command.action.Command;
import by.sazanchuk.finalTask.command.action.CommandResult;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.service.PetService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeletePetCommand implements Command {
    private static final String NAME = "name";
    private static final String USER_ID = "user_id";
    private static final String ERROR_DELETE = "error_delete";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        String name = request.getParameter(NAME);
        // User user = (User) request.getSession().getAttribute(USER);

        try {
            if (name != null || !name.isEmpty()) {
                deletePet(name, request);
                //logger.log(Level.INFO, "user registrated and authorized with login - " + parameters.get(LOGIN));
                return new CommandResult("/controller?command=profile_user", false);
            } else {
                request.setAttribute(ERROR_DELETE, true);
                return goBackWithError(request, "can't delete pet");
            }
        } catch (DaoException | ConnectionPoolException e) {
            return goBackWithError(request, "ERROR");
            //throw new ServiceException(e);

        }

    }


    private void deletePet(String name, HttpServletRequest request) throws DaoException, ServiceException, ConnectionPoolException {
        Integer user_id = (Integer) request.getSession().getAttribute(USER_ID);
        if (user_id != null) {

            ServiceFactory factory = new ServiceFactory();

            PetService service = factory.getService(PetService.class);


            Pet pet;
            pet = service.findByNameAndUserId(name, user_id);
            if (pet != null) {
                service.delete(pet.getIdentity());
            }
        }
    }

    private void setAtributesToSession(Pet pet, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("pet", pet);
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.profile_user"), false);
    }
}


