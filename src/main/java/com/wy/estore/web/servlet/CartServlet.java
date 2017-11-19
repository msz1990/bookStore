package com.wy.estore.web.servlet;

import com.wy.estore.service.BookService;
import com.wy.estore.utils.BaseServlet;
import com.wy.estore.vo.Cart;
import com.wy.estore.vo.CartItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    public Cart getCart(HttpServletRequest req){
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        return cart;
    }
    public String addCart(HttpServletRequest req,HttpServletResponse resp){
        String bid=req.getParameter("bid");
        int count=Integer.parseInt(req.getParameter("count"));
        CartItem cartItem=new CartItem();
        BookService bookService=new BookService();
        cartItem.setBook(bookService.findByBid(bid));
        cartItem.setCount(count);
        Cart cart=getCart(req);
        cart.addCart(cartItem);
        return "/jsps/cart/list.jsp";
    }

    public String clearCart(HttpServletRequest req,HttpServletResponse resp){
        Cart cart=getCart(req);
        cart.clearCart();
        return "/jsps/cart/list.jsp";
    }

    public String removeCart(HttpServletRequest req,HttpServletResponse resp){
        String bid=req.getParameter("bid");
        Cart cart=getCart(req);
        cart.removeCart(bid);
        return "/jsps/cart/list.jsp";
    }
}
