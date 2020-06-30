package com.czj.service;

import com.czj.dao.MenuDao;
import com.czj.dao.UserDao;
import com.czj.entity.Menu;
import com.czj.entity.Page;
import com.czj.entity.User;
import com.czj.utils.MdUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public Page listAll(String name, String pageStr) {
        Page page = new Page<User>();
        //当前页
        if (!StringUtils.isEmpty(pageStr)) {
            page.setPageCurrent(Integer.valueOf(pageStr));
        }
        //总记录数
        page.setCount(userDao.count(name));
        //总数据
        List<User> list = userDao.listAll(name, page);
        page.setData(list);
        return page;
    }

    public void add(User user) {
        user.setId(null);
        user.setRegisterTime(new Date());
        userDao.add(user);
    }

//    public Integer count() {
//        return userDao.count();
//    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public boolean getUserByUserName(String userName) {
        User user = userDao.getUserByUserName(userName);
        if (user == null) {
            return true;
        }
        //账号已存在
        return false;
    }

    public User checkLogin(String name, String password) {
        User user = new User();
        user.setUsername(name);
        password = MdUtil.md5(password);
        user.setPassword(password);
        return userDao.checkLogin(user);
    }

    public void updatePs(String username, String newPs) {
        userDao.updatePs(username, newPs);
    }

    public void updatePic(Integer id, String pic) {
        userDao.updatePic(id,pic);
    }

    public User getUserByWxOpenId(String openid) {
        return userDao.getUserByWxOpenId(openid);
    }
}
