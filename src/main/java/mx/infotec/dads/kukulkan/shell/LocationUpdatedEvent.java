package mx.infotec.dads.kukulkan.shell;

import org.springframework.context.ApplicationEvent;

/**
 * LocationUpdateEvent
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class LocationUpdatedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private String message;

    public LocationUpdatedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
