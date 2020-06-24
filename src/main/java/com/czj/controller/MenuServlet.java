package com.czj.controller;

import com.alibaba.fastjson.JSON;
import com.czj.entity.Menu;
import com.czj.service.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private MenuService menuService = new MenuService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*List<Menu> list = menuService.listAll();
        response.getWriter().write(JSON.toJSONString(list));*/

        ObjectMapper om = new ObjectMapper();
        List<Menu> list = menuService.listAll();
        List<Menu> parent = new ArrayList<>();
        List<Menu> son = new ArrayList<>();

//        for (Menu m : list) {
//            if (m.getType() == 1) {
//                parent.add(m);
//            }
//            if (m.getType() == 2) {
//                son.add(m);
//            }
//        }

        parent = list.stream().filter(n -> {
            return n.getType() == 1;
        }).collect(Collectors.toList());

        son = list.stream().filter(n -> {
            return n.getType() == 2;
        }).collect(Collectors.toList());

        Map<String, List<Menu>> map = new HashMap<>();
        map.put("parent", parent);
        map.put("son", son);

        PrintWriter pw = response.getWriter();
        pw.write(om.writeValueAsString(map));
        pw.close();

    }
}
