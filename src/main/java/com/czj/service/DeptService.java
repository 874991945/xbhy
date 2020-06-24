package com.czj.service;

import com.czj.dao.DeptDao;
import com.czj.dao.MenuDao;
import com.czj.entity.Dept;
import com.czj.entity.Menu;

import java.util.List;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
public class DeptService {
    private DeptDao deptDao = new DeptDao();

    public List<Dept> listAll() {
        return deptDao.listAll();
    }
}
