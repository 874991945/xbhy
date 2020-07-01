package com.czj.service;

import com.czj.dao.UserDao;
import com.czj.entity.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * @auth czj
 * @date 2020/7/1
 * @Description
 */
public class PoiService {

    private UserDao userDao = new UserDao();

    public Workbook userExport(String name) {
        List<User> list = userDao.listForExcel(name);

        //创建excel表
        Workbook wb = new XSSFWorkbook();
        //创建sheet
        Sheet sheet = wb.createSheet("hello");
        //头信息数组
        String[] heads={"部门","用户名","真实姓名","年龄","性别"};
        //创建头
        Row r1 = sheet.createRow(0);
        //添加头信息
        for (int i = 0; i < heads.length; i++) {
            r1.createCell(i).setCellValue(heads[i]);
        }

        for (int i = 1; i <= list.size(); i++) {
            Row row = sheet.createRow(i);
            User user=list.get(i-1);
            row.createCell(0).setCellValue(user.getDeptName()==null?"":user.getDeptName());
            row.createCell(1).setCellValue(user.getUsername()==null?"":user.getUsername());
            row.createCell(2).setCellValue(user.getRealName()==null?"":user.getRealName());
            row.createCell(3).setCellValue(user.getAge()==null?0:user.getAge());
            row.createCell(4).setCellValue(user.getSexName()==null?"":user.getSexName());
        }

        return wb;
    }


}
