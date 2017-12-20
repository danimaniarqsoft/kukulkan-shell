package mx.infotec.dads.kukulkan.shell.commands.fiware;

import static mx.infotec.dads.kukulkan.shell.util.Constants.NULL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.shell.domain.ShellCommand;
import mx.infotec.dads.kukulkan.shell.services.CommandService;

import static mx.infotec.dads.kukulkan.shell.commands.docker.DockerCommands.DOCKER_COMMAND;

/**
 * Orion Context Broker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class OrionCommands {

    @Autowired
    CommandService commandService;

    private boolean isRunning;

    @ShellMethod("Create a instance of the Orion Context Broker")
    public void orionStart(@ShellOption(defaultValue = "1026") String port) {
        commandService.exec(new ShellCommand(DOCKER_COMMAND).addArg("run").addArg("-d").addArg("--name")
                .addArg("-orion").addArg("-p").addArg(port + ":1026").addArg("fiware/orion"));
    }

    @ShellMethod("Stop a instance of the Orion Context Broker")
    public void orionStop(@ShellOption(defaultValue = NULL) String containerId) {
        commandService.exec(new ShellCommand(DOCKER_COMMAND).addArg("start").addArg(containerId));
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
