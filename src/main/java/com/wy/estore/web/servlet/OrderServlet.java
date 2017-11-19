package com.wy.estore.web.servlet;

import com.wy.estore.service.OrderService;
import com.wy.estore.utils.BaseServlet;
import com.wy.estore.utils.PaymentUtil;
import com.wy.estore.utils.UUIDUtils;
import com.wy.estore.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class OrderServlet extends BaseServlet{

    public String callBack(HttpServletRequest req, HttpServletResponse resp) {
        String oid=req.getParameter("r6_order");
        String r3_Amt=req.getParameter("r3_Amt");

        OrderService orderService=new OrderService();
        Order order=orderService.findByOid(oid);
        order.setState(2);
        orderService.update(order);
        req.setAttribute("msg","Pay sunccess");

        return "/jsps/msg.jsp";
    }

    public String payOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oid=req.getParameter("oid");
        String address=req.getParameter("address");
        String pd_FrpId=req.getParameter("pd_FrpId");

        OrderService orderService=new OrderService();
        Order order=orderService.findByOid("oid");
        order.setAddress(address);
        orderService.update(order);

        String p0_Cmd = "Buy";
        String p1_MerId = "10001126856";
        String p2_Order = oid;
        String p3_Amt = "0.01";
        String p4_Cur = "CNY";
        String p5_Pid = "";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        String p8_Url = "http://192.168.14.66:8080/estore/orderServlet?method=callBack";
        String p9_SAF = "";
        String pa_MP = "";
        String pr_NeedResponse = "1";
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, keyValue);
        StringBuffer sb=new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);
        resp.sendRedirect(sb.toString());
        return null;
    }

    public String findByOid(HttpServletRequest req, HttpServletResponse resp){
        String oid=req.getParameter("oid");
        OrderService orderService=new OrderService();
        Order order= orderService.findByOid(oid);
        req.setAttribute("order",order);
        return "/jsps/order/desc.jsp";
    }

    public String findByUid(HttpServletRequest req, HttpServletResponse resp){
        User user= (User) req.getSession().getAttribute("existUser");
        OrderService orderService=new OrderService();
        List<Order> list=orderService.findByUid(user.getUid());
        req.setAttribute("list",list);
        return "/jsps/order/list.jsp";
    }
    public String saveOrder(HttpServletRequest req, HttpServletResponse resp){
        Order order=new Order();
        order.setOid(UUIDUtils.getUUID());
        order.setOrdertime(new Date());
        order.setState(1);
        order.setAddress(null);
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            req.setAttribute("msg","You don't buy anthing yet");
            return "/jsps/msg.jsp";
        }
        double total=cart.getTotal();
        order.setTotal(total);
        User user= (User) req.getSession().getAttribute("existUser");
        if(user == null){
            req.setAttribute("msz","Please sign in");
            return "/jsps/user/login.jsp";
        }
        order.setUser(user);
        for(CartItem cartItem:cart.getCartItems()){
            OrderItem orderItem=new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setItemid(UUIDUtils.getUUID());
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }

        OrderService orderService=new OrderService();
        orderService.save(order);
        cart.clearCart();
        req.setAttribute("order",order);
        return "/jsps/order/desc.jsp";
    }
}
