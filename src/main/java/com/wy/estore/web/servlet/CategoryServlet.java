package com.wy.estore.web.servlet;

import com.wy.estore.service.CategoryService;
import com.wy.estore.utils.BaseServlet;
import com.wy.estore.vo.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends BaseServlet {
        public String findAll(HttpServletRequest req,HttpServletResponse resp){
            CategoryService categoryService=new CategoryService();
            List<Category> list=categoryService.findAll();
            req.setAttribute("list",list);
            return "/jsps/left.jsp";
        }
}
