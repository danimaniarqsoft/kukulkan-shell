package mx.infotec.dads.kukulkan.shell.commands.docker;

import static mx.infotec.dads.kukulkan.shell.util.Constants.NULL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.domain.ProjectContext;
import mx.infotec.dads.kukulkan.shell.domain.ShellCommand;
import mx.infotec.dads.kukulkan.shell.services.CommandService;

/**
 * Docker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class DockerCommands {

    public static final String DOCKER_COMMAND = "docker";

    @Autowired
    ProjectContext projectContext;

    @Autowired
    CommandService commandService;

    @ShellMethod("Show the current docker process running")
    public void dockerPs() {
        commandService.exec(new ShellCommand(DOCKER_COMMAND).addArg("ps"));
    }

    @ShellMethod("Show the current docker process running")
    public void dockerStop(
            @ShellOption(valueProvider = DockerStopValueProvider.class, defaultValue = NULL) String containerId) {
        commandService.exec(new ShellCommand(DOCKER_COMMAND).addArg("stop").addArg(containerId));
    }

    @ShellMethod("Show the current docker process running")
    public void dockerStart(
            @ShellOption(valueProvider = DockerStartValueProvider.class, defaultValue = NULL) String containerId) {
        commandService.exec(new ShellCommand(DOCKER_COMMAND).addArg("start").addArg(containerId));
    }

    @ShellMethodAvailability({ "dockerShowRunningProcess", "dockerStop" })
    public Availability dockerShowRunningProcessAvailability() {
        NativeCommand dockerCmd = projectContext.getAvailableCommands().get(DOCKER_COMMAND);
        if (dockerCmd != null && dockerCmd.isActive()) {
            return Availability.available();
        } else {
            return Availability.unavailable("you must install docker");
        }
    }
}
