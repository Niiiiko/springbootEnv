package com.example.springmem.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springmem.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TokenService
 * @Auther: niko
 * @Date: 2024/10/16 16:07
 * @Description:
 */
@Service("TokenService")
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;
    public String getToken(User user){
        String jwt;
        jwt = JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(secret));
        return jwt;
    }
}
