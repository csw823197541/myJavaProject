package listener;

import java.util.EventListener;

/**
 * Created by csw on 2016/11/17 10:16.
 * Explain:定义监听接口，负责监听DoorEvent事件
 */
public interface DoorListener extends EventListener {

    void doorEvent(DoorEvent doorEvent);
}
