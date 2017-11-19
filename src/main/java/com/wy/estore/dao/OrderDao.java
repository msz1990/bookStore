package com.wy.estore.dao;

import com.wy.estore.utils.JDBCUtils;
import com.wy.estore.vo.Book;
import com.wy.estore.vo.Order;
import com.wy.estore.vo.OrderItem;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderDao {
    public void save(Connection conn, Order order) {
        QueryRunner queryRunner=new QueryRunner();
        String sql="insert into orders values(?,?,?,?,?,?)";
        Object[] params={order.getOid(),order.getTotal(),order.getOrdertime(),order.getState(),order.getAddress(),order.getUser().getUid()};
        try {
            queryRunner.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public void save(Connection conn, OrderItem orderItem) {
        QueryRunner queryRunner=new QueryRunner();
        String sql="insert into orderItem values(?,?,?,?,?)";
        Object[] params={orderItem.getItemid(),orderItem.getCount(),orderItem.getSubtotal(),orderItem.getBook().getBid(),orderItem.getOrder().getOid()};
        try {
            queryRunner.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Order> findByUid(String uid) {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from orders where uid=? order by ordertime desc";
        List<Order> list;
        try {
            list= queryRunner.query(sql,new BeanListHandler<Order>(Order.class),uid);
            sql="select * from orderitem o, book b where o.bid=b.bid and o.oid=?";
            for(Order order:list){
               List<Map<String,Object>> oList= queryRunner.query(sql,new MapListHandler(),order.getOid());
               for(Map<String,Object> map:oList){
                   Book book=new Book();
                   BeanUtils.populate(book,map);
                   OrderItem orderItem=new OrderItem();
                   BeanUtils.populate(orderItem,map);
                   orderItem.setBook(book);
                   order.getOrderItems().add(orderItem);
               }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public Order findByOid(String oid) {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from orders where oid=?";
        Order order;
        try {
           order=queryRunner.query(sql,new BeanHandler<Order>(Order.class),oid);
            sql="select * from orderitem o, book b where o.bid=b.bid and o.oid=?";
            List<Map<String,Object>> oList= queryRunner.query(sql,new MapListHandler(),oid);
            for(Map<String,Object> map:oList){
                Book book=new Book();
                BeanUtils.populate(book,map);
                OrderItem orderItem=new OrderItem();
                BeanUtils.populate(orderItem,map);
                orderItem.setBook(book);
                order.getOrderItems().add(orderItem);
            }
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void update(Order order) {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        String sql="update orders set total=? ,ordertime=?,state=?,address=? where oid=?";
        Object[] params={order.getTotal(),order.getOrdertime(),order.getState(),order.getOid()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
