package by.gsu.epamlab.filters;

import by.gsu.epamlab.controllers.ControllerConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = ControllerConstants.LOGIN_FILTER_URL)
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ControllerConstants.KEY_USER);
        if (login == null){
            session.invalidate();
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect(request.getContextPath() + ControllerConstants.INDEX_PAGE);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }
}
