package javaClassLoader;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by csw on 2016/11/29 20:07.
 * Explain:
 */
public class Process {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static synchronized void doSleep(String inThreadId){
        System.out.println(inThreadId + " " + sdf.format(new Date()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(inThreadId + " " + sdf.format(new Date()));
    }
    public void doProcess(String inThreadId){

        doSleep(inThreadId);
    }
}
