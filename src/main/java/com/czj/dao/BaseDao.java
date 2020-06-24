package com.czj.dao;

import com.czj.utils.DBUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
public class BaseDao {

    public JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());
}
