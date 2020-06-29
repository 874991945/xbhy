package com.czj.dao;

import com.czj.entity.Meeting;
import com.czj.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth czj
 * @date 2020/6/29
 * @Description
 */
public class MeetingDao extends BaseDao {
    public List<Meeting> listAll() {
        String sql = "select * from meeting";
        return template.query(sql, new BeanPropertyRowMapper<Meeting>(Meeting.class));
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
}
