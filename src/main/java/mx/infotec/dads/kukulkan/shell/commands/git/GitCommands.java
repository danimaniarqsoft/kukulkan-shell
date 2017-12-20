package mx.infotec.dads.kukulkan.shell.commands.git;

import static mx.infotec.dads.kukulkan.shell.util.Constants.GIT;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import mx.infotec.dads.kukulkan.shell.commands.publishers.EventType;
import mx.infotec.dads.kukulkan.shell.commands.publishers.LocationChangeEventPublisher;
import mx.infotec.dads.kukulkan.shell.domain.Line;
import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.domain.Navigator;
import mx.infotec.dads.kukulkan.shell.domain.ProjectContext;
import mx.infotec.dads.kukulkan.shell.util.Console;

/**
 * Docker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class GitCommands {

    public static final String GIT_COMMAND = "git";

    @Autowired
    ProjectContext projectContext;

    @Autowired
    Navigator nav;

    @Autowired
    private LocationChangeEventPublisher publisher;

    @ShellMethod("Create a new Feature")
    public void gitCreateFeature(@NotNull String name) {
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "checkout", "-b", "feature-" + name, "develop");
        publisher.publishEvent(EventType.FILE_NAVIGATION);
    }

    @ShellMethod("Terminate a Feature")
    public void gitTerminateFeature(@NotNull String name) {
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "checkout", "develop");
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "merge", "--no-f", "name");
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "branch", "-d", "name");
        publisher.publishEvent(EventType.FILE_NAVIGATION);
    }

    @ShellMethod("Publish a Feature to a remote server")
    public void gitPublishFeature(@NotNull String name) {
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "push", "origin", "develop");
        publisher.publishEvent(EventType.FILE_NAVIGATION);
    }

    @ShellMethod("Create a new Release")
    public void gitCreateRelease(@NotNull String name) {
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "checkout", "-b", "release-" + name, "develop");
        publisher.publishEvent(EventType.FILE_NAVIGATION);
    }

    @ShellMethod("Terminate a Release")
    public void gitTerminateRelease(@NotNull String name) {
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "checkout", "develop");
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "merge", "--no-f", "name");
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "branch", "-d", "name");
        publisher.publishEvent(EventType.FILE_NAVIGATION);
    }

    @ShellMethod("Publish a Release to a remote server")
    public void gitPublishRelease(@NotNull String name) {
        Console.exec(nav.getCurrentPath(), GIT_COMMAND, "push", "origin", "develop");
        publisher.publishEvent(EventType.FILE_NAVIGATION);
    }

    @ShellMethodAvailability({ "gitCreateFeature", "gitTerminateFeature", "gitPublishFeature", "gitCreateRelease",
            "gitTerminateRelease", "gitPublishRelease" })
    public Availability dockerShowRunningProcessAvailability() {
        NativeCommand gitCmd = projectContext.getAvailableCommands().get(GIT_COMMAND);
        if (gitCmd != null && gitCmd.isActive()) {
            return Availability.available();
        } else {
            return Availability.unavailable("you must install git");
        }
    }
}
