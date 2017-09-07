package by.gsu.epamlab.controllers.fileControllers;

import by.gsu.epamlab.controllers.AbstractController;
import by.gsu.epamlab.controllers.ControllerConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.factories.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "DownloadFileController", urlPatterns = ControllerConstants.DOWNLOAD_FILE_CONTROLLER)
public class DownloadFileController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute(ControllerConstants.KEY_USER);
        int idTask = Integer.parseInt(req.getParameter(ControllerConstants.KEY_ID_TASK));
        String fileName = req.getParameter(ControllerConstants.KEY_FILE_NAME);
        String realPath = req.getServletContext().getRealPath(ControllerConstants.SLASH);
        resp.setContentType(ControllerConstants.CONTENT_TYPE_APPLICATION_OCTET_STREAM);
        resp.setHeader(ControllerConstants.CONTENT_DISPOSITION_HEADER, String.format(ControllerConstants.CONTENT_DISPOSITION_VALUE_FORMAT, fileName));
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            ITaskDAO iTaskDAO = DAOFactory.getDAO(ITaskDAO.class);
            inputStream = iTaskDAO.getFileInputStream(login, fileName, idTask, realPath);
            outputStream = resp.getOutputStream();
            int data;
            while ((data = inputStream.read()) != -1){
                outputStream.write(data);
            }
        } catch (DaoException e) {
            jumpError(e.getMessage(), ControllerConstants.ERROR_PAGE, req, resp);
        }finally {
            if (inputStream != null){
                inputStream.close();
            }
            if (outputStream != null){
                outputStream.close();
            }
        }
    }
}
