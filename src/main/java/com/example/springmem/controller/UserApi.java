package com.example.springmem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springmem.bean.User;
import com.example.springmem.service.TokenService;
import com.example.springmem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserApi
 * @Auther: niko
 * @Date: 2024/10/16 16:27
 * @Description:
 */
@RestController("api")
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @RequestMapping("/login")
    public Object login(User user){
        JSONObject jsonObject = new JSONObject();
        User u = userService.login(user);
        if (u == null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!u.getPass().equals(u.getPass())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(u);
                jsonObject.put("token", token);
                jsonObject.put("user", u);
                return jsonObject;
            }
        }
    }
    @RequestMapping("/login2")
    public Object login2(User user){
        JSONObject jsonObject = new JSONObject();
        User u = userService.login(user);
        if (u == null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!u.getPass().equals(u.getPass())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(u);
                jsonObject.put("token", token);
                jsonObject.put("user", u);
                return jsonObject;
            }
        }
    }
    @RequestMapping("/info")
    public String info(){
        return "info";
    }

}
