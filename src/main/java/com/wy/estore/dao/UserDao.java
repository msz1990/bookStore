package com.wy.estore.dao;

import com.wy.estore.utils.JDBCUtils;
import com.wy.estore.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    public void save(User user) {
        try {
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "insert into user values(?,?,?,?,?,?)";
            Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getEmail(), user.getState(), user.getCode()};
            queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Insert Fail");
        }
    }

    public User findByUserName(String username) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from user where username=?";
        try {
            User user=queryRunner.query(sql, new BeanHandler<User>(User.class),username);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public User findByCode(String code) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from user where code=?";
        try {
            User user=queryRunner.query(sql, new BeanHandler<User>(User.class),code);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void update(User user) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql="update user set username= ?, password=?, email=?, state=?, code=? where uid =?";
        Object[] params = {user.getUsername(), user.getPassword(), user.getEmail(), user.getState(), user.getCode(),user.getUid()};
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public User findByUserNameAndPassword(User user) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from user where username=? and password=? and state=?";
        try {
            return queryRunner.query(sql,new BeanHandler<User>(User.class),user.getUsername(),user.getPassword(),1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
