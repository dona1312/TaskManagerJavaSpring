package com.taskmanager.filters;

import com.taskmanager.services.auth.AuthenticationService;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

public class AuthorizationFilter  implements Filter{
    private FilterConfig config = null;

    @Override
    public void  init(FilterConfig config){
        this.config = config;
        config.getServletContext().log("Initializing SessionCheckerFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(!AuthenticationService.getLoggedUser().getIsAdmin()){
            response.sendRedirect(request.getContextPath() + "/tasks/getMyTask");
        } else {
            RequestDispatcher dispatcher=request.getRequestDispatcher(request.getRequestURI());
            dispatcher.forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
