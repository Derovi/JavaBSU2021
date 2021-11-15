package by.zhabdex.quizzer;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author derovi
 */

@Component
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (!httpRequest.getRequestURL().toString().contains("login")
            && httpRequest.getSession().getAttribute("login") == null) {
            httpResponse.sendRedirect("/login");
        }
        chain.doFilter(request, response);
    }
}
