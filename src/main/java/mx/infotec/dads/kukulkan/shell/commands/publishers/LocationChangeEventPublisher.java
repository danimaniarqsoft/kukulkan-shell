package mx.infotec.dads.kukulkan.shell.commands.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * LocationChangeEventPublisher
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Component
public class LocationChangeEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final EventType event) {
        applicationEventPublisher.publishEvent(new LocationUpdatedEvent(this, event));
    }
}
