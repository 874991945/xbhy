package com.czj.controller;

import com.czj.entity.Dept;
import com.czj.entity.User;
import com.czj.service.DeptService;
import com.czj.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();
    private DeptService deptService = new DeptService();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据用户名模糊查询
        String name = request.getParameter("username");
        name=name==null?"":name;

        //获取页数
        String pageStr = request.getParameter("page");

        request.setAttribute("username", name);
        request.setAttribute("page", userService.listAll(name,pageStr));
        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();

        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userService.add(user);
        response.sendRedirect("/user/list");

    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return;
        }
        User user = userService.getUserById(Integer.valueOf(id));
        List<Dept> deptList = deptService.listAll();

        request.setAttribute("user", user);
        request.setAttribute("deptList", deptList);
        request.getRequestDispatcher("/jsp/user/update.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.update(user);
        response.sendRedirect("/user/list");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return;
        }
        userService.delete(Integer.valueOf(id));
        response.sendRedirect("/user/list");
    }

    public void getUserByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        //name==null || "".equals(name)
        if (StringUtils.isEmpty(name)) {
            return;
        }
        boolean b = userService.getUserByUserName(name);
        if (b) {
            out.write("1");
        } else {
            //已存在
            out.write("0");
        }
        out.close();
    }

}
