package com.czj.service;

import com.czj.dao.MeetingDao;
import com.czj.entity.Meeting;
import com.czj.entity.Page;
import com.czj.utils.DateUtil;
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
        meeting.setPublishDate(DateUtil.getDateStr(new Date()));
        meeting.setStartTime(meeting.getStartTime().replace("T"," "));
        meeting.setId(null);
        meeting.setDeptId(null);
        meeting.setStatus(0);
        meetingDao.add(meeting);
    }

    public void delete(Integer id) {
        meetingDao.delete(id);
    }

    public Meeting getMeetingById(Integer id) {
        return meetingDao.getMeetingById(id);
    }

    public void updateStatusTask() {
        List<Meeting> list = meetingDao.listAll();
        for (Meeting meeting : list) {
            //当前时间戳
            long currentTime = new Date().getTime();
            long startTime = DateUtil.getTimeByStr(meeting.getStartTime());
            long endTime = DateUtil.getTimeByStr(meeting.getEndTime());

            if (startTime <= currentTime) {
                if (endTime > currentTime) {
                    //会议正在进行中
                    meetingDao.updateStatus(meeting.getId(),1);
                } else {
                    //会议已经结束
                    meetingDao.updateStatus(meeting.getId(),2);
                }
            } else {
                //会议未开始，不需要处理
            }

        }
    }
}
