package com.czj.dao;

import com.czj.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
public class UserDao extends BaseDao {

    public List<User> listAll() {
        return template.query("select * from user", new BeanPropertyRowMapper<User>(User.class));
    }

    public void add(User user) {
        String sql = "insert into user (username,password,email,real_name,age,sex,description,register_time,dept_id) values (?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRealName(),
                user.getAge(), user.getSex(), user.getDescription(), user.getRegisterTime(), user.getDeptId());
    }

    public Integer count() {
        String sql = "select count(1) from user";
        try {
            return template.queryForObject(sql, Integer.class);
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
