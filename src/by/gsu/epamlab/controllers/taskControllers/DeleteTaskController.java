package by.gsu.epamlab.controllers.taskControllers;

import by.gsu.epamlab.controllers.AbstractController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.IDataConverter;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.converter.DataConverter;
import by.gsu.epamlab.model.factories.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "DeleteTaskController", urlPatterns = ControllerConstants.DELETE_TASK_CONTROLLER)
public class DeleteTaskController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String)req.getSession().getAttribute(ControllerConstants.KEY_USER);
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        IDataConverter dataConverter = new DataConverter();
        Integer[] idTasksToDelete = dataConverter.getTasksFromJson(json);
        try {
            ITaskDAO iTaskDAO = DAOFactory.getDAO(ITaskDAO.class);
            iTaskDAO.deleteTask(login, idTasksToDelete);
            redirect(req.getContextPath() + ControllerConstants.TASK_CONTROLLER, resp);
        } catch (DaoException e) {
            jumpError(e.getMessage(), ControllerConstants.ERROR_PAGE, req, resp);
        }
    }
}
