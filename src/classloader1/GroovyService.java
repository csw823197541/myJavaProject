package classloader1;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by csw on 2020/05/08.
 * Description:
 */
public class GroovyService {

    private static Map<String, Class> groovyClassCache = new ConcurrentHashMap<>();

    private GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    /**
     * 用于调用指定Groovy脚本中的指定方法
     *
     * @param script 脚本对象
     * @param params 方法参数
     * @return Object       方法返回值
     */
    public Object exec(Script script, Object... params) {
        Object res = null;

        try {
            Class clazz = getClass(script.getScriptName(), script.getCode(), script.getVersion());
            GroovyObject instance = (GroovyObject) clazz.newInstance();
            res = instance.invokeMethod(script.getScriptMethod(), params);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("执行脚本发生异常：\n" + getStackTrace(e));
        }

        return res;
    }

    private Class getClass(String scriptName, String code, String version) {
        String currentKey = generateKey(scriptName, version);
        if (groovyClassCache.get(currentKey) == null) {
            Class clazz = groovyClassLoader.parseClass(code);
            removeClass(generateKeyPrefix(scriptName)); // 去除已经存在同名的脚本
            groovyClassCache.put(currentKey, clazz);
        }
        return groovyClassCache.get(currentKey);
    }

    private void removeClass(String prefix) {
        for (String key : groovyClassCache.keySet()) {
            if (key.startsWith(prefix)) {
                groovyClassCache.remove(prefix);
            }
        }
    }

    private String generateKey(String scriptName, String version) {
        return generateKeyPrefix(scriptName) + version;
    }

    private String generateKeyPrefix(String scriptName) {
        return "groovy-" + scriptName + "-";
    }

    private static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
