package listener;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by csw on 2016/11/17 10:23.
 * Explain:事件源对象,(如果是在swing中，就类似一个button)
 */
public class DoorManager {

    private Set<DoorListener> listeners;

    public void addDoorListener(DoorListener listener) {
        if (listeners == null) {
            listeners = new HashSet<>();
        }
        listeners.add(listener);
    }

    public void removeDoorListener(DoorListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }

    /**
     * 通知所有的DoorListener
     * @param event
     */
    private void notifyListeners(DoorEvent event) {
        for (DoorListener listener : listeners) {
            listener.doorEvent(event);
        }
    }

    /**
     *
     */
    protected void openDoor() {
        if (listeners == null)
            return;
        DoorEvent event = new DoorEvent(this, "open");
        notifyListeners(event);
    }

    /**
     *
     */
    protected void closeDoor() {
        if (listeners == null)
            return;
        DoorEvent event = new DoorEvent(this, "close");
        notifyListeners(event);
    }
}
