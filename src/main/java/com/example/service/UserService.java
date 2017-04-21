package com.example.service;

import com.example.Utils.MD5;
import com.example.domain.FlightEntity;
import com.example.domain.TicketEntity;
import com.example.domain.UserEntity;
import com.example.repository.FlightRepository;
import com.example.repository.UserRepository;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/**
 * Created by iam24 on 17/4/1.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FlightRepository flightRepository;
    public String register(UserEntity userEntity){
        if (!userEntity.CheckPersonID()){
            return "身份证错误";
        }
        if (userRepository.findByName(userEntity.getName()) == null){
            userRepository.save(userEntity);
            return "注册成功";
        }
        else {
            return "用户名已经被使用";
        }
    }

    public  String login(String name, String password, HttpSession session){
        UserEntity dbuser = userRepository.findByName(name);
        if (dbuser == null){
            return "用户不存在";
        }
        if (!dbuser.getPassword().equals(password)){
            return "密码错误!";
        }
        session.setAttribute("user",dbuser);
        if (dbuser.getRole_id() == 1) {
            return "登陆成功1";
        }else
            return "登录成功3";
    }

    public String editPassword(String old_password, String new_password, HttpSession session){

       UserEntity userEntity = (UserEntity) session.getAttribute("user");
       if (userEntity == null) return "请先登录!";
       if (!MD5.getMD5(old_password).equals(userEntity.getPassword())){
           return "密码错误!";
       }
        userEntity.setPassword(new_password);
        userRepository.save(userEntity);
        return "修改成功!";
    }

    public String delete(long id, HttpSession session){
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        if (userEntity.getRole_id() != 1){
            return "权限不足!";
        }
        if (userRepository.findOne(id) == null) {return "用户不存在!";}
        userRepository.delete(id);
        return "删除成功!";
    }

//    public ArrayList<UserEntity> findall(){
//        return userRepository.findAll();
//    }

    public ArrayList<UserEntity> FindAllUser(){
        return userRepository.findAllUser();
    }

    /**
     * 导入航班信息
     * @param file
     * @return
     * @throws IOException
     */
    public String Importexcel(MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        int totrows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < totrows ; i++ ){
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell0 = row.getCell(0);
            XSSFCell cell1 = row.getCell(1);
            XSSFCell cell2 = row.getCell(2);
            XSSFCell cell3 = row.getCell(3);
            XSSFCell cell4 = row.getCell(4);
            FlightEntity flightEntity = new FlightEntity((int)cell0.getNumericCellValue(), (int) cell1.getNumericCellValue(),
                    cell2.getStringCellValue(), (int)cell3.getNumericCellValue(), (int)cell4.getNumericCellValue());
            flightRepository.save(flightEntity);
        }
        return "导入成功!";
    }

    /**
     * 导出航班信息
     * @return
     * @throws IOException
     */
    public String downloadexcel() throws  IOException{
        String filepath = "/Users/iam24/Documents/JAVA/flight-ticket/flight_info.xlsx";
        ArrayList<FlightEntity> flightEntities = flightRepository.findAll();
        XSSFWorkbook workbook =  new XSSFWorkbook();
        OutputStream out = new FileOutputStream(filepath);
        XSSFSheet sheet = workbook.createSheet("info");
        int row_num = 0;
        XSSFRow row = sheet.createRow(row_num);
        row.createCell(0).setCellValue("航班号");
        row.createCell(1).setCellValue("飞机号");
        row.createCell(2).setCellValue("目的地");
        row.createCell(3).setCellValue("订票数");
        row.createCell(4).setCellValue("余票数");

        for (FlightEntity i : flightEntities) {
            row_num++;
            row = sheet.createRow(row_num);
            row.createCell(0).setCellValue(i.getFlight());
            row.createCell(1).setCellValue(i.getPlane_id());
            row.createCell(2).setCellValue(i.getDestination());
            row.createCell(3).setCellValue(i.getBooked_ticket());
            row.createCell(4).setCellValue(i.getRemain_ticket());
        }

        workbook.write(out);
        return "导出成功!";
    }
}
