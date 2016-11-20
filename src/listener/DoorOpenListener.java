package listener;

/**
 * Created by csw on 2016/11/17 10:19.
 * Explain:定义事件监听类，这些类具体实现了监听功能和事件处理功能。
 */
public class DoorOpenListener implements DoorListener {

    @Override
    public void doorEvent(DoorEvent event) {
        if (event.getDoorState() != null && event.getDoorState().equals("open")) {
            System.out.println("门1打开");
        } else {
            System.out.println("门1关闭");
        }
    }
}
