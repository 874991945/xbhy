package com.czj.dao;

import com.czj.entity.Meeting;
import com.czj.entity.Menu;
import com.czj.entity.Page;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth czj
 * @date 2020/6/29
 * @Description
 */
public class MeetingDao extends BaseDao {
    public List<Meeting> listAll(String name, Page page) {
        String sql = "select m.*,d.name from meeting m left join dept d on m.dept_id=d.id where m.title like ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<Meeting>(Meeting.class),
                "%"+name+"%",(page.getPageCurrent()-1)*page.getSize(),page.getSize());
    }

    public void add(Meeting meeting) {
        String sql = "insert into meeting (title,content,publish_date,start_time,end_time,status,make_user,dept_id) values (?,?,?,?,?,?,?,?)";
        template.update(sql,meeting.getTitle(),meeting.getContent(),meeting.getPublishDate(),meeting.getStartTime(),
                meeting.getEndTime(),meeting.getStatus(),meeting.getMakeUser(),meeting.getDeptId());
    }

    public void delete(Integer id) {
        String sql = "delete from meeting where id=?";
        template.update(sql, id);
    }

    public Integer count(String title) {
        String sql = "select count(1) from meeting where title like ?";
        try {
            return template.queryForObject(sql, Integer.class,"%"+title+"%");
        } catch (DataAccessException e) {
            return 0;
        }
    }

    public Meeting getMeetingById(Integer id) {
        String sql = "select * from meeting where id=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Meeting.class), id);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
