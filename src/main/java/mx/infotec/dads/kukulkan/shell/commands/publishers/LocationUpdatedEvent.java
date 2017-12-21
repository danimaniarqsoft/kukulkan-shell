package mx.infotec.dads.kukulkan.shell.commands.publishers;

import java.io.Serializable;

/**
 * LocationUpdateEvent
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class LocationUpdatedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private EventType eventType;

    private String message;

    public LocationUpdatedEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
