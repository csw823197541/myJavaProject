package classloader

/**
 * Created by csw on 2016/11/29 18:18.
 * Explain: 
 */
class TestGroovyJob {

    public void execute(){


        println "aaaaa"
        doSleep()
        println "bbbbb"
    }
    public synchronized void doSleep(){
        Thread.sleep(1000);
    }
}
