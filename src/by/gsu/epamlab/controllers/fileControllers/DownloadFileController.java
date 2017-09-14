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
import java.io.*;

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
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ITaskDAO iTaskDAO = DAOFactory.getDAO(ITaskDAO.class);
            bis = new BufferedInputStream(iTaskDAO.getFileInputStream(login, fileName, idTask, realPath));
            bos = new BufferedOutputStream(resp.getOutputStream());
            int bytes;
            while ((bytes = bis.read()) !=-1){
                bos.write(bytes);
            }
        } catch (DaoException e) {
            jumpError(e.getMessage(), ControllerConstants.ERROR_PAGE, req, resp);
        }finally {
            if (bis != null){
                bis.close();
            }
            if (bos != null){
                bos.close();
            }
        }
    }
}
