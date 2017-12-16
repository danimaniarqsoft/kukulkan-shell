package mx.infotec.dads.kukulkan.shell.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CommandsConfiguration implements ApplicationListener<ApplicationReadyEvent>{

    private final Logger log = LoggerFactory.getLogger(CommandsConfiguration.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent arg0) {
        System.out.println("THe app is ready!!!!!");
       log.info("The app is ready!!!!!!!!!");        
    }
}
