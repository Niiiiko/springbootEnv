package com.example.springmem.controller;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: VulSPEL
 * @Auther: niko
 * @Date: 2024/10/30 17:02
 * @Description:
 */
@RestController
public class VulSPEL {
    @RequestMapping("/spel")
    public String vulspel(@RequestParam String name){
        SpelExpressionParser parser = new SpelExpressionParser();//创建解析器
        Expression expression = parser.parseExpression(name);
        return (String) expression.getValue();
    }

}
