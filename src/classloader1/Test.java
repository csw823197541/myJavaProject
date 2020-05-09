package classloader1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by csw on 2020/05/08.
 * Description:
 */
public class Test {

    public static void main(String[] args) {

        String code = "package classloader1\n" +
                "\n" +
                "/**\n" +
                " * Created by csw on 2020/05/08.\n" +
                " * Description: \n" +
                " */\n" +
                "class Groovy1 {\n" +
                "\n" +
                "    public Script execute(Script script, Map map) {\n" +
                "        println(\"我是脚本：\" + script.getScriptName() + \"，正在执行。。。\")\n" +
                "        println(\"我从参数中取到了值：\" + map.get(\"name\"))\n" +
                "        return script\n" +
                "    }\n" +
                "}";
        Script script = new Script();
        script.setScriptName("groovy1");
        script.setScriptMethod("execute");
        script.setVersion("1");
        script.setCode(code);

        GroovyService groovyService = new GroovyService();

        Map<Object, Object> map = new HashMap<>();
        map.put("name", "csw");
        Object object = groovyService.exec(script, script, map);
        Script script1 = (Script) object;
        System.out.println(script1.getScriptMethod());

//        script.setVersion("");
//        Object object1 = groovyService.exec(script);
//        System.out.println(object1.toString());
    }
}
