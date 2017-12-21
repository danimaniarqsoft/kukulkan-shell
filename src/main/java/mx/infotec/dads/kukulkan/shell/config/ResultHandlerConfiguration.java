package mx.infotec.dads.kukulkan.shell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.infotec.dads.kukulkan.shell.handlers.ShellResultHandler;

@Configuration
public class ResultHandlerConfiguration {

    @Bean
    public ShellResultHandler shellResultHandlerConfiguration() {
        return new ShellResultHandler();
    }
}
