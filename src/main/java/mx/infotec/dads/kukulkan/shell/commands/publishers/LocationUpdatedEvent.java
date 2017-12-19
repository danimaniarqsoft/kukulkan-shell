package mx.infotec.dads.kukulkan.shell.commands.publishers;

import org.springframework.context.ApplicationEvent;

/**
 * LocationUpdateEvent
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class LocationUpdatedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private EventType eventType;

    private String message;
    

    public LocationUpdatedEvent(Object source, EventType eventType) {
        super(source);
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
