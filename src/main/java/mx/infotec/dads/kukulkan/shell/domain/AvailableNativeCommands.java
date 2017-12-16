package mx.infotec.dads.kukulkan.shell.domain;

import static mx.infotec.dads.kukulkan.shell.domain.NativeCommandType.DOCKER;
import static mx.infotec.dads.kukulkan.shell.domain.NativeCommandType.DOCKER_COMPOSE;
import static mx.infotec.dads.kukulkan.shell.domain.NativeCommandType.GIT;
import static mx.infotec.dads.kukulkan.shell.domain.NativeCommandType.MAVEN;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * AvailableNativeCommands list the main commands that kukulkan use for main
 * task.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class AvailableNativeCommands {

    private static final Map<NativeCommandType, NativeCommand> commmands;

    static {
        commmands = new HashMap<>();
        commmands.put(DOCKER, new NativeCommand(DOCKER));
        commmands.put(DOCKER_COMPOSE, new NativeCommand(DOCKER_COMPOSE));
        commmands.put(GIT, new NativeCommand(GIT));
        commmands.put(MAVEN, new NativeCommand(MAVEN));
    }

    public static Collection<NativeCommand> getNativeCommands() {
        return commmands.values();
    }

    public static boolean isDockerInstalled() {
        return commmands.get(DOCKER).isActive();
    }

    public static boolean isDockerComposeInstalled() {
        return commmands.get(DOCKER_COMPOSE).isActive();
    }

    public static boolean isGitInstalled() {
        return commmands.get(GIT).isActive();
    }
    
    public static boolean isMavenInstalled() {
        return commmands.get(MAVEN).isActive();
    }

}
