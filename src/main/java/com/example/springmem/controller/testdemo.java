package com.example.springmem.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @ClassName: testdemo
 * @Auther: niko
 * @Date: 2024/10/28 17:34
 * @Description:
 */
public class testdemo {
    public static void main(String[] args) throws IOException {
        String templateString = new String(Files.readAllBytes(Paths.get("./src/main/resources/templates/template.vm")));
        System.out.println(templateString);
    }

}
