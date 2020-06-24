package com.czj.dao;

import com.czj.entity.Dept;
import com.czj.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
public class DeptDao extends BaseDao{
    public List<Dept> listAll() {
        String sql = "select * from dept";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }
}
