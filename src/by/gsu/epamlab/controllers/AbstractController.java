package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exceptions.ValidationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        performTask(req, resp);
    }

    protected abstract void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected void jumpError(String message, String jumpLocation, HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ControllerConstants.KEY_ERROR_MESSAGE, message);
        RequestDispatcher rd = request.getServletContext().getRequestDispatcher(jumpLocation);
        rd.forward(request, response);
    }
    protected void jump(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
    protected void redirect(String url, HttpServletResponse response) throws IOException {
        response.sendRedirect(url);
    }

    protected void checkCredentials(String login, String password) throws ValidationException {
        if (login == null || password == null){
            throw new ValidationException(ControllerConstants.LOGIN_OR_PASSWORD_ABSENT_ERROR);
        }
        login = login.trim();
        if (ControllerConstants.EMPTY_LINE.equals(login)){
            throw new ValidationException(ControllerConstants.LOGIN_ERROR_EMPTY);
        }
    }

    protected void checkCredentials(String login, String password, String passwordRepeat) throws ValidationException {
        if (!password.equals(passwordRepeat)){
            throw new ValidationException(ControllerConstants.PASSWORD_AND_PASSWORD_REPEAT_ARE_NOT_EQUALS);
        }
        checkCredentials(login, password);
    }
}
