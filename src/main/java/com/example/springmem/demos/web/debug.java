package com.example.springmem.demos.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import java.io.*;
import java.util.Base64;

/**
 * @ClassName: debug
 * @Auther: niko
 * @Date: 2024/9/2 18:13
 * @Description:
 */
@RestController
public class debug {
    @RequestMapping("/debug")
    public String debug(){
//        AbstractHandlerMethodMapping
        return "debug";
    }
    @RequestMapping("/ser")
    public String ser(String base64) throws IOException {
        byte[] decode;
        try {
            decode = Base64.getDecoder().decode(base64);
        } catch (Exception e) {
            System.out.printf(base64);
            decode = new sun.misc.BASE64Decoder().decodeBuffer(base64);
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(decode);
        ObjectInputStream ois = null;
        ois = new ObjectInputStream(bis);
        try {
            ois.readObject();
            return "ok";
        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
        return "fail";
    }

}
