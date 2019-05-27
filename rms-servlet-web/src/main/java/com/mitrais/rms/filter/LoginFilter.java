package com.mitrais.rms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String name = (String) httpReq.getSession().getAttribute("name");
        if(name != null && !name.equals("")){ // sudah login
            chain.doFilter(request, response);
        } else {
            httpReq.getRequestDispatcher("/login").forward(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}