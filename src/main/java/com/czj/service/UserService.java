package com.czj.service;

import com.czj.dao.MenuDao;
import com.czj.dao.UserDao;
import com.czj.entity.Menu;
import com.czj.entity.User;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public List<User> listAll() {
        return userDao.listAll();
    }

    public void add(User user){
        user.setId(null);
        user.setRegisterTime(new Date());
        user.setDeptId(null);
        userDao.add(user);
    }

    public Integer count() {
        return userDao.count();
    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public boolean getUserByUserName(String userName) {
        User user = userDao.getUserByUserName(userName);
        if (user == null) {
            return true;
        }
        //账号已存在
        return false;
    }
}
