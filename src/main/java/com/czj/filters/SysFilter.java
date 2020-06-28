package com.czj.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.czj.constants.SysConstant;
import com.czj.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */

@WebFilter("/*")
public class SysFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        //规定登录地址为.../index.jsp
        if (uri.endsWith("/index.jsp")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals(SysConstant.COOKIE_REMEMBER)) {
                        String cookieValue = cookie.getValue();
                        cookieValue = URLDecoder.decode(cookieValue, "utf-8");
                        User user = JSON.parseObject(cookieValue, new TypeReference<User>() {
                        });
                        session.setAttribute(SysConstant.COOKIE_REMEMBER, user);
                        session.setMaxInactiveInterval(60);

                        //有cookie就放行，跳转到主页面
                        filterChain.doFilter(request, response);
                        request.getRequestDispatcher("/jsp/common/main.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } else if (uri.endsWith("/") ||
                uri.endsWith("/login") ||
                uri.endsWith("/forget.jsp") ||
                uri.endsWith("/email") ||
                uri.endsWith("/forget") ||
                uri.endsWith("/menu") ||
        uri.endsWith("/addUserName.jsp")) {
            //直接放行
            filterChain.doFilter(request, response);
            return;
        } else {
            if (session.getAttribute(SysConstant.SESSION_LOGIN) == null) {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        filterChain.doFilter(request, response);

    }
}
