package mx.infotec.dads.kukulkan.shell.commands.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.shell.LocationUpdatedEvent;

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

    public void doStuffAndPublishAnEvent(final String message) {
        System.out.println("Publishing custom event. "+message);
        LocationUpdatedEvent lue = new LocationUpdatedEvent(this, "from event");
        applicationEventPublisher.publishEvent(lue);
    }

}
