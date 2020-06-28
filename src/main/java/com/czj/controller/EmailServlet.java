package com.czj.controller;

import com.czj.constants.SysConstant;
import com.czj.entity.User;
import com.czj.service.UserService;
import com.czj.utils.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */

@WebServlet("/email")
public class EmailServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email=request.getParameter("email");

        HttpSession session=request.getSession();
        PrintWriter pw=response.getWriter();
        if (!(email == null || "".equals(email))) {
            //随机验证码
            String randomCode = Math.random() + "";
            randomCode = randomCode.substring(randomCode.length() - 5, randomCode.length() - 1);
            boolean b = EmailUtil.send(email, randomCode);
            if (b) {
                //把验证码放到session中
                session.setAttribute(SysConstant.SESSION_CODE, randomCode);
                session.setMaxInactiveInterval(60);
                pw.write("1");
                return;
            }
        }
        pw.write("0");
        pw.close();

    }
}
