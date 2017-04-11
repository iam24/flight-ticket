package com.example.service;

import com.example.Utils.MD5;
import com.example.domain.UserEntity;
import com.example.repository.UserRepository;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
        return "登陆成功";
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
        if (userEntity == null) {return "请先登录!";}
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

    public ArrayList<UserEntity> findAllUser(){
        return userRepository.findAllUser();
    }
}
