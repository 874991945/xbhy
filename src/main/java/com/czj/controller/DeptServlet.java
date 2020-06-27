package com.czj.controller;

import com.alibaba.fastjson.JSON;
import com.czj.entity.Dept;
import com.czj.service.DeptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet{

    private DeptService deptService=new DeptService();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Dept>deptList=deptService.listAll();
        request.setAttribute("deptList",deptList);
        response.getWriter().write(JSON.toJSONString(deptList));
    }
}
