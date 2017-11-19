package com.wy.estore.web.servlet;

import com.wy.estore.service.BookService;
import com.wy.estore.service.CategoryService;
import com.wy.estore.utils.BaseServlet;
import com.wy.estore.vo.Book;
import com.wy.estore.vo.Category;
import com.wy.estore.vo.PageBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    public String findByBid(HttpServletRequest req,HttpServletResponse resp){
        String bid=req.getParameter("bid");
        BookService bookService=new BookService();
        Book book=bookService.findByBid(bid);
        req.setAttribute("book",book);

        return "/jsps/book/desc.jsp";
    }
    public String findByPage(HttpServletRequest req,HttpServletResponse resp){
        int currPage = Integer.parseInt(req.getParameter("currPage"));
        String cid=req.getParameter("cid");
        BookService bookService=new BookService();
        PageBean<Book> pageBean=null;
        if(cid==null)
            pageBean=bookService.findByPage(currPage);
        else
            pageBean=bookService.findByPageAndCid(currPage,cid);
        req.setAttribute("pageBean",pageBean);
        return "/jsps/book/list.jsp";
    }
}
