package com.czj.controller;

import com.czj.entity.Meeting;
import com.czj.entity.User;
import com.czj.service.MeetingService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @auth czj
 * @date 2020/6/29
 * @Description
 */

@WebServlet("/meeting/*")
public class MeetingServlet extends BaseServlet{

    private MeetingService meetingService=new MeetingService();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title=request.getParameter("title");
        title=title==null?"":title;

        //获取页数
        String pageStr = request.getParameter("page");

        request.setAttribute("page", meetingService.listAll(title,pageStr));
        request.setAttribute("title", title);

        request.getRequestDispatcher("/jsp/meeting/list.jsp").forward(request,response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        Meeting meeting=new Meeting();
        try {
            BeanUtils.populate(meeting, map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        meetingService.add(meeting);
        response.sendRedirect("/meeting/list");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id==null) {
            return;
        }
        meetingService.delete(Integer.valueOf(id));
        response.sendRedirect("/meeting/list");
    }

    public void getMeetingById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id=request.getParameter("id");
        Meeting meeting=meetingService.getMeetingById(Integer.valueOf(id));
        request.setAttribute("meeting",meeting);

        request.getRequestDispatcher("/jsp/meeting/showMeeting.jsp").forward(request,response);
    }
}
