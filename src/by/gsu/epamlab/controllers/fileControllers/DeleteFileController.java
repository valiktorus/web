package by.gsu.epamlab.controllers.fileControllers;

import by.gsu.epamlab.controllers.AbstractPostController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.factories.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteFileController", urlPatterns = ControllerConstants.DELETE_FILE_CONTROLLER)
public class DeleteFileController extends AbstractPostController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ControllerConstants.CHARACTER_ENCODING);
        String login = (String) req.getSession().getAttribute(ControllerConstants.KEY_USER);
        int idTask = Integer.parseInt(req.getParameter(ControllerConstants.KEY_ID_TASK));
        String fileName = req.getParameter(ControllerConstants.KEY_FILE_NAME);
        String realPath = getServletContext().getRealPath(ControllerConstants.SLASH);
        try {
            ITaskDAO iTaskDAO = DAOFactory.getDAO(ITaskDAO.class);
            iTaskDAO.deleteFile(login, fileName, idTask, realPath);
            redirect(req.getContextPath() + ControllerConstants.TASK_CONTROLLER, resp);
        } catch (DaoException e) {
            jumpError(e.getMessage(), ControllerConstants.ERROR_PAGE, req, resp);
        }
    }
}
