package listener;

import java.util.EventObject;

/**
 * Created by csw on 2016/11/17 10:13.
 * Explain:定义事件对象，必须继承EventObject
 */
public class DoorEvent extends EventObject {

    private String doorState = "";

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DoorEvent(Object source, String doorState) {
        super(source);
        this.doorState = doorState;
    }

    public String getDoorState() {
        return doorState;
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState;
    }
}
