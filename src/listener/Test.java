package listener;

import observer.MySubject;
import observer.Observer1;
import observer.Observer2;

/**
 * Created by csw on 2016/11/17 9:21.
 * Explain:主程序，就想象成要开门的哪个人
 */
public class Test {

    public static void main(String[] args) {
        DoorManager manager = new DoorManager();
        manager.addDoorListener(new DoorOpenListener());// 给门1增加监听器
        manager.addDoorListener(new LightOpenListener());// 给门2增加监听器
        // 开门
        manager.openDoor();
        System.out.println("我已经进来了");
        // 关门
        manager.closeDoor();
    }
}
