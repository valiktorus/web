package by.gsu.epamlab.controllers.taskControllers;

import by.gsu.epamlab.controllers.AbstractController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.IDataConverter;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.converter.DataConverter;
import by.gsu.epamlab.model.enums.ActionEnum;
import by.gsu.epamlab.model.factories.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ChangeTaskController", urlPatterns = ControllerConstants.CHANGE_TASK_CONTROLLER)
public class ChangeTaskController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        ActionEnum action = ActionEnum.valueOf(reader.readLine().toUpperCase());
        IDataConverter dataConverter = new DataConverter();
        Task[] tasksToChange = dataConverter.getTasksFromJson(json);
        String login = (String) req.getSession().getAttribute(ControllerConstants.KEY_USER);
        try {
            ITaskDAO iTaskDAO = DAOFactory.getDAO(ITaskDAO.class);
            iTaskDAO.updateTasks(login, tasksToChange, action);
            redirect(ControllerConstants.TASK_CONTROLLER, resp);
        } catch (DaoException e) {
            jumpError(e.getMessage(), ControllerConstants.ERROR_PAGE, req, resp);
        }
    }
}
