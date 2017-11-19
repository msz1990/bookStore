package com.wy.estore.web.filter;

import com.wy.estore.vo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class PrivilegeFilter implements javax.servlet.Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        User existUser= (User) req.getSession().getAttribute("existUser");
        if(existUser != null){
            filterChain.doFilter(req,servletResponse);
        }else{
            req.setAttribute("msg","You are not sign in");
            req.getRequestDispatcher("/jsps/msg.jsp").forward(req,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
