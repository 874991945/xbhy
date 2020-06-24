package com.czj.dao;

import com.czj.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
public class MenuDao extends BaseDao{
    public List<Menu> listAll() {
        String sql = "select * from menu";
        return template.query(sql, new BeanPropertyRowMapper<Menu>(Menu.class));
    }
}
