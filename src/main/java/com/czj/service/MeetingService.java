package com.czj.service;

import com.czj.dao.MeetingDao;
import com.czj.entity.Meeting;
import com.czj.entity.Page;
import com.czj.entity.User;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @auth czj
 * @date 2020/6/29
 * @Description
 */
public class MeetingService {
    private MeetingDao meetingDao=new MeetingDao();

    public Page listAll(String title, String pageStr) {
        Page page = new Page<Meeting>();
        //当前页
        if (!StringUtils.isEmpty(pageStr)) {
            page.setPageCurrent(Integer.valueOf(pageStr));
        }
        //总记录数
        page.setCount(meetingDao.count(title));

        List<Meeting> list = meetingDao.listAll(title, page);
        page.setData(list);
        return page;
    }

    public void add(Meeting meeting) {
        meeting.setPublishDate(new Date());
        meeting.setId(null);
        meeting.setDeptId(null);
        meetingDao.add(meeting);
    }

    public void delete(Integer id) {
        meetingDao.delete(id);
    }

    public Meeting getMeetingById(Integer id) {
        return meetingDao.getMeetingById(id);
    }
}
