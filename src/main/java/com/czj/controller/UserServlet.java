package com.czj.controller;

import com.czj.entity.User;
import com.czj.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

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
 * @date 2020/6/23
 * @Description
 */

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    private UserService userService=new UserService();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name=request.getParameter("username");
        request.setAttribute("username",name);

        //获取总记录数
        Integer count=userService.count();
        String pageStr=request.getParameter("page");

        List<User>list=userService.listAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]>map= request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userService.add(user);
        response.sendRedirect("/user/list");

    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
