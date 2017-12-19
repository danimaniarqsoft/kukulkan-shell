package mx.infotec.dads.kukulkan.shell.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.infotec.dads.kukulkan.shell.domain.DefaultNativeCommand;
import mx.infotec.dads.kukulkan.shell.services.NativeCommandProvided;

/**
 * NativeCommandConfig. The main commands needed by the app are registred here.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Configuration
public class NativeCommandConfig {

    @Bean
    public NativeCommandProvided dockerCommand() {
        return () -> Optional.of(new DefaultNativeCommand("docker", "docker version"));
    }

    @Bean
    public NativeCommandProvided dockerComposeCommand() {
        return () -> Optional.of(new DefaultNativeCommand("docker-compose", "docker-compose version"));
    }

    @Bean
    public NativeCommandProvided gitCommand() {
        return () -> Optional.of(new DefaultNativeCommand("git", "git version"));
    }

    @Bean
    public NativeCommandProvided javaCommand() {
        return () -> Optional.of(new DefaultNativeCommand("java", "java -version"));
    }

    @Bean
    public NativeCommandProvided kukulkanCommand() {
        return () -> Optional.of(new DefaultNativeCommand("kukulkan", "kukulkan -version"));
    }
}
