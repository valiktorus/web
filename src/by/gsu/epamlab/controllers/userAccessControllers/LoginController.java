package by.gsu.epamlab.controllers.userAccessControllers;

import by.gsu.epamlab.controllers.AbstractPostController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.UserAuthenticationException;
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

@WebServlet(name = "LoginController", urlPatterns = ControllerConstants.LOGIN_CONTROLLER)
public class LoginController extends AbstractPostController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String login = req.getParameter(ControllerConstants.KEY_LOGIN);
            String password = req.getParameter(ControllerConstants.KEY_PASSWORD);
            checkCredentials(login, password);
            IUserDAO userDAO = DAOFactory.getDAO(IUserDAO.class);
            User user = userDAO.getUser(login.trim(), password);
            HttpSession session = req.getSession();
            session.setAttribute(ControllerConstants.KEY_USER, user.getLogin());
            jump(ControllerConstants.TASK_CONTROLLER, req, resp);
        }catch (ValidationException | DaoException | UserAuthenticationException e){
            jumpError(e.getMessage(), ControllerConstants.LOGIN_PAGE, req, resp);
        }
    }
}
