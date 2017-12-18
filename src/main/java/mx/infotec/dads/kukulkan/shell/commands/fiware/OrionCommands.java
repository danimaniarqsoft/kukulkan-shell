package mx.infotec.dads.kukulkan.shell.commands.fiware;

import static mx.infotec.dads.kukulkan.shell.util.Constants.NULL;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.shell.util.Console;
import static mx.infotec.dads.kukulkan.shell.util.Constants.DOCKER_COMMAND;

/**
 * Orion Context Broker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class OrionCommands {
    private boolean isRunning;


    @ShellMethod("Create a instance of the Orion Context Broker")
    public void orionStart(@ShellOption(defaultValue = "1026") String port) {
        Console.exec(DOCKER_COMMAND, "run", "-d", "--name", "orion", "-p", port + ":1026", "fiware/orion");
        isRunning = true;
    }

    @ShellMethod("Stop a instance of the Orion Context Broker")
    public void orionStop(@ShellOption(defaultValue = NULL) String containerId) {
        Console.exec(DOCKER_COMMAND, "start", containerId);
        isRunning = false;
    }


    @ShellMethodAvailability({ "dockerShowRunningProcess", "dockerStop" })
    public Availability orionStartAvailability() {
        if (!isRunning) {
            return Availability.available();
        } else {
            return Availability.unavailable("you have a Orion Context Broker instance running");
        }
    }
}
