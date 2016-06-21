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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(AuthenticationService.getLoggedUser() == null){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        else if(AuthenticationService.getLoggedUser() != null){
            RequestDispatcher dispatcher=request.getRequestDispatcher(request.getRequestURI());
            dispatcher.forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
