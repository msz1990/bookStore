package com.wy.estore.service;

import com.wy.estore.dao.OrderDao;
import com.wy.estore.utils.JDBCUtils;
import com.wy.estore.vo.Order;
import com.wy.estore.vo.OrderItem;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    public void save(Order order) {
        Connection conn=null;
        try {
            conn= JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            OrderDao orderDao=new OrderDao();
            orderDao.save(conn,order);
            for(OrderItem orderItem:order.getOrderItems()){
                orderDao.save(conn,orderItem);
            }
            DbUtils.commitAndCloseQuietly(conn);
        } catch (SQLException e) {
            DbUtils.rollbackAndCloseQuietly(conn);
            e.printStackTrace();
        }
    }

    public List<Order> findByUid(String uid) {
        OrderDao orderDao=new OrderDao();
        return orderDao.findByUid(uid);
    }

    public Order findByOid(String oid) {
        OrderDao orderDao=new OrderDao();
        return orderDao.findByOid(oid);
    }

    public void update(Order order) {
        OrderDao orderDao=new OrderDao();
        orderDao.update(order);
    }
}
