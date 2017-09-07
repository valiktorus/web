package by.gsu.epamlab.controllers.taskControllers;

import by.gsu.epamlab.controllers.AbstractController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.enums.TaskEnum;
import by.gsu.epamlab.model.factories.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TaskController", urlPatterns = ControllerConstants.TASK_CONTROLLER)
public class TaskController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ControllerConstants.CHARACTER_ENCODING);
        String login = (String) req.getSession().getAttribute(ControllerConstants.KEY_USER);
        try{
            ITaskDAO userDAO = DAOFactory.getDAO(ITaskDAO.class);
            Map<TaskEnum, List<Task>> taskLists = userDAO.getTaskLists(login);
            List<Task> todayList = taskLists.get(TaskEnum.TODAY);
            List<Task> tomorrowList = taskLists.get(TaskEnum.TOMORROW);
            List<Task> otherDayList = taskLists.get(TaskEnum.OTHER);
            List<Task> fixedList = taskLists.get(TaskEnum.FIXED);
            List<Task> deletedList = taskLists.get(TaskEnum.DELETED);
            req.setAttribute(ControllerConstants.TODAY_LIST, todayList);
            req.setAttribute(ControllerConstants.TOMORROW_LIST, tomorrowList);
            req.setAttribute(ControllerConstants.OTHER_DAY_LIST, otherDayList);
            req.setAttribute(ControllerConstants.FIXED_LIST, fixedList);
            req.setAttribute(ControllerConstants.DELETED_LIST, deletedList);
            jump(ControllerConstants.MAIN_PAGE,req,  resp);
        }catch (DaoException e){
            jumpError(e.getMessage(), ControllerConstants.ERROR_PAGE, req, resp);
        }
    }
}
