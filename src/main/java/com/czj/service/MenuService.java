package com.czj.service;

import com.czj.dao.MenuDao;
import com.czj.entity.Menu;
import java.util.List;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
public class MenuService {
    private MenuDao menuDao = new MenuDao();

    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
