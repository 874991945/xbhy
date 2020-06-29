package com.czj.service;

import com.czj.dao.MeetingDao;
import com.czj.entity.Meeting;

import java.util.Date;
import java.util.List;

/**
 * @auth czj
 * @date 2020/6/29
 * @Description
 */
public class MeetingService {
    private MeetingDao meetingDao=new MeetingDao();

    public List<Meeting> listAll() {
        return meetingDao.listAll();
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
}
