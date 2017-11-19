package com.wy.estore.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String methodName=req.getParameter("method");
        Class clazz=this.getClass();
        try {
            Method method=clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            if(method==null){
                resp.getWriter().print("No such method");
                return;
            }else{
                String result=(String)method.invoke(this,req,resp);
                if(result != null){
                    req.getRequestDispatcher(result).forward(req,resp);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
