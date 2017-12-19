package mx.infotec.dads.kukulkan.shell.commands.docker;

import static mx.infotec.dads.kukulkan.shell.util.Constants.NULL;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.shell.domain.AvailableNativeCommands;
import mx.infotec.dads.kukulkan.shell.util.Console;
import static mx.infotec.dads.kukulkan.shell.util.Constants.DOCKER_COMMAND;

/**
 * Docker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class DockerCommands {

    @ShellMethod("Show the current docker process running")
    public void dockerPs() {
        Console.exec(DOCKER_COMMAND, "ps");
    }

    @ShellMethod("Show the current docker process running")
    public void dockerStop(
            @ShellOption(valueProvider = DockerStopValueProvider.class, defaultValue = NULL) String containerId) {
        Console.exec(DOCKER_COMMAND, "stop", containerId);
    }

    @ShellMethod("Show the current docker process running")
    public void dockerStart(
            @ShellOption(valueProvider = DockerStartValueProvider.class, defaultValue = NULL) String containerId) {
        Console.exec(DOCKER_COMMAND, "start", containerId);
    }

    @ShellMethodAvailability({ "dockerShowRunningProcess", "dockerStop" })
    public Availability dockerShowRunningProcessAvailability() {
        if (AvailableNativeCommands.isDockerInstalled()) {
            return Availability.available();
        } else {
            return Availability.unavailable("you must install docker");
        }
    }
}