package com.wy.estore.dao;

import com.wy.estore.utils.JDBCUtils;
import com.wy.estore.vo.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    public List<Category> findAll() {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        String sql="select * from category";
        try {
            List<Category> list=queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
