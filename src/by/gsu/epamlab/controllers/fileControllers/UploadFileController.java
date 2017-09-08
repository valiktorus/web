package by.gsu.epamlab.controllers.fileControllers;

import by.gsu.epamlab.controllers.AbstractController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.factories.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "UploadFileController", urlPatterns = ControllerConstants.UPLOAD_FILE_CONTROLLER)
@MultipartConfig(maxFileSize = ControllerConstants.MAX_FILE_SIZE)
public class UploadFileController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(ControllerConstants.CHARACTER_ENCODING);
        String login = (String) req.getSession().getAttribute(ControllerConstants.KEY_USER);
        int idTask = Integer.parseInt(req.getParameter(ControllerConstants.KEY_ID_TASK));
        Part part = req.getPart(ControllerConstants.KEY_FILE_UPLOAD);
        String realPath = req.getServletContext().getRealPath(ControllerConstants.SLASH);
        String fileName = part.getSubmittedFileName();
        try {
            ITaskDAO iTaskDAO = DAOFactory.getDAO(ITaskDAO.class);
            iTaskDAO.uploadFile(login, idTask, realPath, fileName, part.getInputStream());
            redirect(req.getContextPath() + ControllerConstants.TASK_CONTROLLER, resp);
        } catch (DaoException e) {
            jumpError(e.getMessage(), ControllerConstants.ERROR_PAGE, req, resp);
        }
    }
}
