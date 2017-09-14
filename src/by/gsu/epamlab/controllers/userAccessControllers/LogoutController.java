package by.gsu.epamlab.controllers.userAccessControllers;

import by.gsu.epamlab.controllers.AbstractPostController;
import by.gsu.epamlab.controllers.ControllerConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutController", urlPatterns = ControllerConstants.LOGOUT_CONTROLLER)
public class LogoutController extends AbstractPostController {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        redirect(req.getContextPath() + ControllerConstants.INDEX_PAGE, resp);
    }
}