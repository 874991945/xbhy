package com.czj.controller;

import com.alibaba.fastjson.JSON;
import com.czj.entity.Dept;
import com.czj.service.DeptService;
import com.czj.service.PoiService;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
@WebServlet("/poi/*")
public class PoiServlet extends BaseServlet{

    PoiService poiService=new PoiService();

    public void userExportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name=request.getParameter("username");
        name=name==null?"":name;
        Workbook wb = poiService.userExport(name);

        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String d=sdf.format(date);
        String str=d+".xlsx";

        response.setHeader("Content-Disposition", "attachment;filename=" + new String(str.getBytes("utf-8"), "iso-8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");

        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);

    }
}
