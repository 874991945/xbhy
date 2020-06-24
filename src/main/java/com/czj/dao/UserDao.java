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

    //增加数据
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

    //删除
    public void delete(Integer id) {
        String sql = "delete from user where id=?";
        template.update(sql, id);
    }

    //修改
    public User getUserById(Integer id) {
        String sql = "select * from user where id=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    //验证用户名是否存在
    public User getUserByUserName(String userName) {
        String sql = "select * from user where username=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userName);
        } catch (DataAccessException e) {
            return null;
        }
    }

}
