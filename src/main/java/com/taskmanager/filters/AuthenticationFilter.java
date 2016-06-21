package com.taskmanager.filters;

import com.taskmanager.services.auth.AuthenticationService;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

public class AuthenticationFilter  implements Filter{
    private FilterConfig config = null;

    @Override
    public void  init(FilterConfig config){
        this.config = config;
        config.getServletContext().log("Initializing AuthenticationFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(AuthenticationService.getLoggedUser() == null){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            response.sendRedirect(request.getContextPath() + "/login");
        }
        else if(AuthenticationService.getLoggedUser() != null){
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

            RequestDispatcher dispatcher=httpRequest.getRequestDispatcher(httpRequest.getRequestURI());
            dispatcher.forward(servletRequest,servletResponse);

            return;
        }
    }

    @Override
    public void destroy() {

    }
}
