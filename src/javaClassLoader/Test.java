package javaClassLoader;

import javax.tools.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;

/**
 * Created by csw on 2016/11/29 19:32.
 * Explain:
 */
public class Test {

    public final static Class<?> compile(String name, String content) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StrSrcJavaObject srcObject = new StrSrcJavaObject(name, content);
        Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, fileObjects);
        boolean result = task.call();
        if (result == true) {
            System.out.println("Compile it successfully.");
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            /*ClassLoader loader = Test.class.getClassLoader();
            Class<?> cls;
            try
            {
                cls = loader.loadClass(name);
                return cls;
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }*/
        }

        return null;
    }

    public static void main(String[] args) {

        String content = "package javaClassLoader;\n" +
                "\n" +
                "import java.text.SimpleDateFormat;\n" +
                "import java.util.Date;\n" +
                "\n" +
                "/**\n" +
                " * Created by csw on 2016/11/29 20:07.\n" +
                " * Explain:\n" +
                " */\n" +
                "public class Process {\n" +
                "\n" +
                "    static SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss:SSS\");\n" +
                "\n" +
                "    public static synchronized void doSleep(String inThreadId){\n" +
                "        System.out.println(inThreadId + \" \" + sdf.format(new Date()));\n" +
                "        try {\n" +
                "            Thread.sleep(1000);\n" +
                "        } catch (InterruptedException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "        System.out.println(inThreadId + \" \" + sdf.format(new Date()));\n" +
                "    }\n" +
                "    public void doProcess(String inThreadId){\n" +
                "\n" +
                "        doSleep(inThreadId);\n" +
                "    }\n" +
                "}\n";

        try {
            for (int i = 0; i < 10; i++) {
                String j = i + "";
                Class<?> cls = compile("javaClassLoader.Process", content);
                Object o = cls.newInstance();
                System.out.println(cls + " " + o);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Method method = null;
                        try {
                            method = cls.getMethod("doProcess", String.class);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        try {
                            method.invoke(o, j);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class StrSrcJavaObject extends SimpleJavaFileObject {

        private String content;

        public StrSrcJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }

    }
}
