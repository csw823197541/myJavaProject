package classloader

import queue.Producer

/**
 * Created by csw on 2016/11/29 18:18.
 * Explain: 
 */
class MyTestLoader {
    public static void main(String[] args) {
        println "start test!"

        String content = """
import java.text.SimpleDateFormat
class Processer {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")

    public static synchronized void doSleep(String inThreadId){
        println( inThreadId + " " + sdf.format(new Date()))
        Thread.sleep(1000)
        println( inThreadId + " " + sdf.format(new Date()))
    }
    public void doProcess(String inThreadId){

        doSleep(inThreadId)
    }

}

        """.trim()


        GroovyClassLoader groovyClassLoader1 = new GroovyClassLoader();
        for (int i = 0; i < 10; i++) {
            String j = "" + i
            Thread t = new Thread(new Runnable() {
                @Override
                void run() {
                    Class groovyClass1 = groovyClassLoader1.parseClass(content);
                    println groovyClass1.getName()
                    GroovyObject groovyObject = groovyClass1.newInstance();
                    groovyObject.doProcess(j)
                }
            })
            t.start()
        }
    }
}
