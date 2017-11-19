package com.wy.estore.service;

import com.wy.estore.dao.BookDao;
import com.wy.estore.vo.Book;
import com.wy.estore.vo.PageBean;

import java.util.List;

public class BookService {
    public PageBean<Book> findByPage(int currPage) {
        BookDao bookDao=new BookDao();
        PageBean<Book> pageBean=new PageBean<>();
        pageBean.setCurrPage(currPage);
        int pageSize=8;
        pageBean.setPageSize(pageSize);
        int totalCount=bookDao.findCount();
        pageBean.setTotalCount(totalCount);
        int totalPage= (int) Math.ceil((double)totalCount/pageSize);
        pageBean.setTotalPage(totalPage);
        int begin = (currPage-1)*pageSize;
        List<Book> list =bookDao.findByPage(begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    public PageBean<Book> findByPageAndCid(int currPage, String cid) {
        BookDao bookDao=new BookDao();
        PageBean<Book> pageBean=new PageBean<>();
        pageBean.setCurrPage(currPage);
        int pageSize=8;
        pageBean.setPageSize(pageSize);
        int totalCount=bookDao.findCountById(cid);
        pageBean.setTotalCount(totalCount);
        int totalPage= (int) Math.ceil((double)totalCount/pageSize);
        pageBean.setTotalPage(totalPage);
        int begin = (currPage-1)*pageSize;
        List<Book> list =bookDao.findByPageAndCid(begin,pageSize,cid);
        pageBean.setList(list);
        return pageBean;
    }

    public Book findByBid(String bid) {
        BookDao bookDao=new BookDao();
        return bookDao.findByBid(bid);
    }
}
