package com.example.springmem.service;

import com.example.springmem.bean.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserService
 * @Auther: niko
 * @Date: 2024/10/16 16:06
 * @Description:
 */
@Service("UserService")
public class UserService {
    public User findUser(String id){
        if ("1".equals(id)){
            User user = new User();
            user.setName("admin");
            user.setPass("admin");
            user.setId("1");
            return user;
        }else {
            return null;
        }
    }
    public User login(User user){
        if (user.getName() == "admin"&& user.getPass() == "admin"){
            user.setName("admin");
            user.setPass("admin");
            user.setId("1");
            return user;
        }else {
            return user;
        }
    }

}
