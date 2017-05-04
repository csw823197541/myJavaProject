package classloader

import java.text.SimpleDateFormat

/**
 * Created by csw on 2016/11/29 18:36.
 * Explain: 
 */
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
