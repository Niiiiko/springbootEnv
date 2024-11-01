package com.example.springmem.config;

import freemarker.core.TemplateClassResolver;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ClassName: freemakerConfiguration
 * @Auther: niko
 * @Date: 2024/10/30 14:28
 * @Description:
 */
@Configuration
public class freemakerConfiguration {
    @Autowired
    private freemarker.template.Configuration configuration;
    @PostConstruct
    public void configuration() throws TemplateModelException {
        // 添加全局变量
        this.configuration.setNewBuiltinClassResolver(TemplateClassResolver.SAFER_RESOLVER);
        this.configuration.setAPIBuiltinEnabled(true);
    }
}
