package com.example.Config;

import com.example.domain.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by iam24 on 17/4/13.
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //System.out.println("-------------------preHandle");
        // 验证用户是否登陆
        Object obj = request.getSession().getAttribute("user");
        UserEntity userEntity = new UserEntity();
        userEntity = (UserEntity) obj;
        if (userEntity.getRole_id() != 1){
            response.sendRedirect("/401");
            return false;
        }
//        if (obj == null ) {
//            response.sendRedirect("/login");
//            return false;
//        }
        return true;
    }

}
