package by.gsu.epamlab.controllers.userAccessControllers;

import by.gsu.epamlab.controllers.AbstractController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegistrationController", urlPatterns = ControllerConstants.REGISTRATION_CONTROLLER)
public class RegistrationController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String login = req.getParameter(ControllerConstants.KEY_LOGIN);
            String password = req.getParameter(ControllerConstants.KEY_PASSWORD);
            String passwordRepeat = req.getParameter(ControllerConstants.KEY_PASSWORD_REPEAT);
            checkCredentials(login, password, passwordRepeat);
            IUserDAO userDAO = DAOFactory.getDAO(IUserDAO.class);
            User user = userDAO.registerUser(login.trim(), password);
            HttpSession session = req.getSession();
            session.setAttribute(ControllerConstants.KEY_USER, user.getLogin());
            redirect(req.getContextPath() + ControllerConstants.TASK_CONTROLLER, resp);
        }catch (ValidationException | DaoException e){
            jumpError(e.getMessage(), ControllerConstants.REGISTRATION_PAGE, req, resp);
        }
    }
}