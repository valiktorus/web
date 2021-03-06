package by.gsu.epamlab.controllers.taskControllers;

import by.gsu.epamlab.controllers.AbstractPostController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.factories.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "CreateTaskController", urlPatterns = ControllerConstants.CREATE_TASK_CONTROLLER)
public class CreateTaskController extends AbstractPostController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ControllerConstants.CHARACTER_ENCODING);
        String login = (String) req.getSession().getAttribute(ControllerConstants.KEY_USER);
        String taskDescription = req.getParameter(ControllerConstants.KEY_TASK_DESCRIPTION);
        Date date = Date.valueOf(req.getParameter(ControllerConstants.KEY_DATE));
        try {
            ITaskDAO iTaskDAO = DAOFactory.getDAO(ITaskDAO.class);
            iTaskDAO.createTask(login, new Task(taskDescription, date));
            redirect(req.getContextPath() + ControllerConstants.TASK_CONTROLLER, resp);
        } catch (DaoException e) {
            jumpError(e.getMessage(), ControllerConstants.ERROR_PAGE, req, resp);
        }
    }
}
