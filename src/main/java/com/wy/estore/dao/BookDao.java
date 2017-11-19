package com.wy.estore.dao;

import com.wy.estore.utils.JDBCUtils;
import com.wy.estore.vo.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDao {
    public int findCount() {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select count(*) from book where isdel = 0";
        Long num;
        try {
            num= (Long)queryRunner.query(sql,new ScalarHandler());
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public int findCountById(String cid) {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select count(*) from book where cid=? and isdel = 0";
        Long num;
        try {
            num= (Long)queryRunner.query(sql,new ScalarHandler(),cid);
            return num.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public List<Book> findByPage(int begin, int pageSize) {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from book where isdel = 0 limit ?,?";
        try {
           return queryRunner.query(sql,new BeanListHandler<Book>(Book.class),begin,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Book> findByPageAndCid(int begin, int pageSize, String cid) {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from book where cid=? and isdel = 0 limit ?,?";
        try {
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class),cid,begin,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Book findByBid(String bid) {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from book where bid=?";
        try {
            return queryRunner.query(sql,new BeanHandler<Book>(Book.class),bid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
