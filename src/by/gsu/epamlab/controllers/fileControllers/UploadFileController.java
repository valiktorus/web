package by.gsu.epamlab.controllers.fileControllers;

import by.gsu.epamlab.controllers.AbstractController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.factories.DAOFactory;
import javafx.scene.shape.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(name = "UploadFileController", urlPatterns = ControllerConstants.UPLOAD_FILE_CONTROLLER)
@MultipartConfig()
public class UploadFileController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute(ControllerConstants.KEY_USER);
        Task task = new Task(req.getParameter("taskDescription"), req.getParameter("taskDate"));
        Part part = req.getPart("fileUpload");
        String realPath = req.getServletContext().getRealPath("/");
        String fileName = part.getSubmittedFileName();
        try {
            ITaskDAO iTaskDAO = DAOFactory.getDAO(ITaskDAO.class);
            iTaskDAO.uploadFile(login, task, realPath, fileName, part.getInputStream());
            redirect(ControllerConstants.TASK_CONTROLLER, resp);
        } catch (DaoException e) {
            jumpError(e.getMessage(), ControllerConstants.ERROR_PAGE, req, resp);
        }

    }
}