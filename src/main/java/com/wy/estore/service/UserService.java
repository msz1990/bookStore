package com.wy.estore.service;

import com.wy.estore.dao.UserDao;
import com.wy.estore.utils.MailUtils;
import com.wy.estore.utils.UUIDUtils;
import com.wy.estore.vo.User;

public class UserService {

    public void regist(User user) {
        // Insert record into database
        UserDao userDao=new UserDao();
        user.setUid(UUIDUtils.getUUID());
        user.setState(0);
        String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
        user.setCode(code);
        userDao.save(user);
        // SendEmail
        MailUtils.sendMail(user.getEmail(),code);
    }

    public User findByUserName(String username) {
        UserDao userDao=new UserDao();
        return userDao.findByUserName(username);
    }

    public User findByCode(String code) {
        UserDao userDao=new UserDao();
        return userDao.findByCode(code);
    }

    public void update(User user) {
        UserDao userDao=new UserDao();
        userDao.update(user);
    }

    public User login(User user) {
        UserDao userDao=new UserDao();
        return userDao.findByUserNameAndPassword(user);
    }
}
