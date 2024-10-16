package com.example.springmem.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springmem.bean.User;
import com.example.springmem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AuthInterceptor
 * @Auther: niko
 * @Date: 2024/10/16 16:05
 * @Description:
 */

public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Value("${jwt.secret}")
    String secret;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置401未授权状态码
            response.setContentType("application/json;charset=UTF-8"); // 设置返回类型为 JSON
            response.getWriter().write("{\"error\":\"Unauthorized access. Please login.\"}"); // 自定义返回内容
            return false; // 拦截请求，防止继续处理
//            throw new RuntimeException("no token");
        }
        String id;

                id = JWT.decode(token).getAudience().get(0);
        User user;
        user = userService.findUser(id);
        if (user == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置401未授权状态码
            response.setContentType("application/json;charset=UTF-8"); // 设置返回类型为 JSON
            response.getWriter().write("{\"error\":\"Unauthorized access. Please login.\"}"); // 自定义返回内容
            return false; // 拦截请求，防止继续处理
        }
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.none()).build();
//        try{
//            jwtVerifier.verify(token);
//        }catch (Exception e){
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置401未授权状态码
//            response.setContentType("application/json;charset=UTF-8"); // 设置返回类型为 JSON
//            response.getWriter().write("{\"error\":\"Unauthorized access. Please login.\"}"); // 自定义返回内容
//            return false; // 拦截请求，防止继续处理
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
