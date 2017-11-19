package com.wy.estore.web.servlet;

import com.wy.estore.service.UserService;
import com.wy.estore.utils.BaseServlet;
import com.wy.estore.vo.User;

import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class UserServlet extends BaseServlet {
    public String logout(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath()+"/jsps/main.jsp");
        return null;
    }

    public String login(HttpServletRequest req,HttpServletResponse resp){
        Map<String,String[]> map=req.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
            UserService userService=new UserService();
            User existUser=userService.login(user);
            if(existUser == null){
                req.setAttribute("msg","Username or password is incorrect");
                return "jsps/user/login.jsp";
            }else{
                req.getSession().setAttribute("existUser",existUser);
                resp.sendRedirect(req.getContextPath()+"/jsps/main.jsp");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String active(HttpServletRequest req,HttpServletResponse resp){
        String code=req.getParameter("code");
        UserService userService=new UserService();
        User user=userService.findByCode(code);
        if(user == null){
            req.setAttribute("msg","Incorrect activation code");
        }else{
            user.setState(1);
            user.setCode("");
            userService.update(user);
            req.setAttribute("msg","Active success");
        }
        return "/jsps/msg.jsp";
    }

    public String checkUsername(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String username=req.getParameter("username");
        UserService userService=new UserService();
        User existUser=userService.findByUserName(username);
        if(existUser == null){
            resp.getWriter().println("1");
        }else{
            resp.getWriter().println("2");
        }
        return null;
    }
    /*
    * Regist Method
    * */
    public String regist(HttpServletRequest req,HttpServletResponse resp){
            //Receive data
            Map<String,String[]> map=req.getParameterMap();
            //Data encapsulation
            User user=new User();
        try {
            BeanUtils.populate(user,map);
            //Invoke service
            UserService userService=new UserService();
            userService.regist(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        req.setAttribute("msg","An email already sent to you, click the line to active your account");
        return "/jsps/msg.jsp";
    }
}
