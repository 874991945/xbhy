package com.czj.dao;

import com.czj.entity.Page;
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

    public List<User> listAll(String name, Page page) {
        String sql="select u.*,d.name deptName from user u left join dept d on u.dept_id=d.id where username like ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class),
                "%"+name+"%",(page.getPageCurrent()-1)*page.getSize(),page.getSize());
    }

    //增加数据
    public void add(User user) {
        String sql = "insert into user (username,password,email,real_name,age,sex,description,register_time,dept_id) values (?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRealName(),
                user.getAge(), user.getSex(), user.getDescription(), user.getRegisterTime(), user.getDeptId());
    }

    //总记录数
    public Integer count(String username) {
        String sql = "select count(1) from user where username like ?";
        try {
            return template.queryForObject(sql, Integer.class,"%"+username+"%");
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
    //1.回显数据
    public User getUserById(Integer id) {
        String sql = "select * from user where id=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (DataAccessException e) {
            return null;
        }
    }
    //2.执行修改
    public void update(User user){
        String sql="update user set username=?,email=?,real_name=?,age=?,sex=?,description=?,dept_id=? where id=?";
        template.update(sql,user.getUsername(),user.getEmail(),user.getRealName(),
                user.getAge(),user.getSex(),user.getDescription(),user.getDeptId(),user.getId());
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

    public User checkLogin(User user){
        String sql = "select * from user where username=? and password=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),user.getUsername(),user.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void updatePs(String username, String newPs) {
        String sql = "update user set password=? where username=? ";
        template.update(sql, newPs, username);
    }

}
