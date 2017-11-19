package com.wy.estore.service;

import com.wy.estore.dao.CategoryDao;
import com.wy.estore.vo.Category;

import java.util.List;

public class CategoryService {

    public List<Category> findAll() {
        CategoryDao categoryDao=new CategoryDao();
        return categoryDao.findAll();
    }
}
