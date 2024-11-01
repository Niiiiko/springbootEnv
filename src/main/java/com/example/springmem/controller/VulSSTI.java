package com.example.springmem.controller;

//import org.apache.velocity.Template;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.Velocity;
//import org.apache.velocity.runtime.RuntimeServices;
//import org.apache.velocity.runtime.RuntimeSingleton;
//import org.apache.velocity.runtime.parser.ParseException;
//import org.apache.velocity.runtime.parser.node.SimpleNode;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.Configurable;
import freemarker.core.TemplateClassResolver;
import freemarker.template.Configuration;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static freemarker.core.TemplateClassResolver.SAFER_RESOLVER;
import static freemarker.template.Configuration.*;

/**
 * @ClassName: VulSSTI
 * @Auther: niko
 * @Date: 2024/10/28 16:16
 * @Description:
 */
@Controller
@RequestMapping("/ssti")
public class VulSSTI {
    @RequestMapping("/velocity1")
    public String sstiRce(@RequestParam(name = "content") String content){
        Velocity.init();
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("username","seeii");
        StringWriter stringBuilder = new StringWriter();
        try {
            Velocity.evaluate(velocityContext, stringBuilder, "test", content);
        }catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }
    @RequestMapping("/velocity2")
    public String sstiRce2(@RequestParam(name = "content") String content) throws  IOException, ParseException {
        String templateString = new String(Files.readAllBytes(Paths.get("./src/main/resources/templates/template.vm")));
        templateString = templateString.replace("id", content);

        StringReader reader = new StringReader(templateString);
        VelocityContext ctx = new VelocityContext();
        ctx.put("name", "lisi");
        ctx.put("phone", "111222");
        ctx.put("email", "123@qq.com");
        StringWriter out = new StringWriter();
        Template template = new Template();
        RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
        SimpleNode node = runtimeServices.parse(reader, String.valueOf(template));
        template.setRuntimeServices(runtimeServices);
        template.setData(node);
        template.initDocument();
        template.merge(ctx, out);
        return out.toString();
    }
    @RequestMapping("/freemaker")
    public String helloFreemaker(@RequestBody Map<String,Object> body, Model model){

//        Configuration.setNewBuiltinClassResolver(SAFER_RESOLVER);
        model.addAttribute("name", body.get("name"));
//        freemarker.template.utility.JythonRuntime
        return "hellofreemaker";
    }

@Autowired
private Configuration con;
    @PostMapping("/submit")
    public String submitData(@RequestBody Map<String, String> formData) {
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        for (String templateKey:formData.keySet()){
            stringLoader.putTemplate(templateKey,  formData.get(templateKey));
        }
        con.setTemplateLoader(new MultiTemplateLoader(new TemplateLoader[]{stringLoader,con.getTemplateLoader()}));
        return "editTemplate";
    }
}

