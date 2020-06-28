package com.czj.controller;

import com.alibaba.fastjson.JSON;
import com.czj.constants.SysConstant;
import com.czj.entity.User;
import com.czj.service.UserService;
import com.czj.utils.MdUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */

@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {
    private UserService userService = new UserService();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        HttpSession session = request.getSession();
        User user = userService.checkLogin(name, password);

        if ("1".equals(remember)) {
            Cookie cookie = new Cookie(SysConstant.COOKIE_REMEMBER, URLEncoder.encode(JSON.toJSONString(user), "utf-8"));
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        if (user == null) {
            //账号或密码错误
            response.sendRedirect("/index.jsp");
        } else {
            session.setAttribute(SysConstant.SESSION_LOGIN, user);
            request.getRequestDispatcher("/jsp/common/main.jsp").forward(request, response);
        }
    }

    protected void forget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("username");
        String newPs = request.getParameter("newPs");
        String code = request.getParameter("code");

        HttpSession session = request.getSession();

        //判断账号是否存在
        boolean b = !userService.getUserByUserName(name);

        if (session.getAttribute(SysConstant.SESSION_CODE) != null) {
            //把session中的验证码和前端传递过来的验证做比较
            if (code.equals(session.getAttribute(SysConstant.SESSION_CODE)) && b) {
                //说明验证码正确 & 账号存在
                userService.updatePs(name, MdUtil.md5(newPs));
                System.out.println("修改成功！");
                response.sendRedirect("/index.jsp");
            } else {
                System.out.println("账号不存在或者验证码不正确");
                response.sendRedirect("/jsp/login/forget.jsp");
            }
        }
    }
}
