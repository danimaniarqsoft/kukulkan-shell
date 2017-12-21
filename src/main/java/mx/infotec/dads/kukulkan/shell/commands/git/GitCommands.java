package mx.infotec.dads.kukulkan.shell.commands.git;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.jline.utils.AttributedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import mx.infotec.dads.kukulkan.shell.commands.publishers.EventType;
import mx.infotec.dads.kukulkan.shell.commands.publishers.LocationUpdatedEvent;
import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.domain.Navigator;
import mx.infotec.dads.kukulkan.shell.domain.ProjectContext;
import mx.infotec.dads.kukulkan.shell.domain.ShellCommand;
import mx.infotec.dads.kukulkan.shell.services.CommandService;
import mx.infotec.dads.kukulkan.shell.util.ResultFormatter;

/**
 * Docker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class GitCommands {

    @Autowired
    CommandService commandService;

    public static final String GIT_COMMAND = "git";

    @Autowired
    ProjectContext projectContext;

    @Autowired
    Navigator nav;

    @Autowired
    private ApplicationEventPublisher publisher;

    @ShellMethod("Show the status of the current git project")
    public List<AttributedString> gitStatus() {
        List<CharSequence> exec = commandService.exec(new ShellCommand(GIT_COMMAND, "status"));
        publisher.publishEvent(new LocationUpdatedEvent(EventType.FILE_NAVIGATION));
        return ResultFormatter.formatToGitOutput(exec);
    }

    @ShellMethod("Create a new Feature")
    public void gitCreateFeature(@NotNull String name) {
        commandService.exec(nav.getCurrentPath(),
                new ShellCommand(GIT_COMMAND, "checkout", "-b", "feature-" + name, "develop"));
        publisher.publishEvent(new LocationUpdatedEvent(EventType.FILE_NAVIGATION));
    }

    @ShellMethod("Terminate a Feature")
    public void gitTerminateFeature(@NotNull String name) {
        commandService.exec(nav.getCurrentPath(), new ShellCommand(GIT_COMMAND, "checkout", "develop"));
        commandService.exec(nav.getCurrentPath(),
                new ShellCommand(GIT_COMMAND, GIT_COMMAND, "merge", "--no-f", "name"));
        commandService.exec(nav.getCurrentPath(), new ShellCommand(GIT_COMMAND, GIT_COMMAND, "branch", "-d", "name"));
        publisher.publishEvent(new LocationUpdatedEvent(EventType.FILE_NAVIGATION));
    }

    @ShellMethod("Publish a Feature to a remote server")
    public void gitPublishFeature(@NotNull String name) {
        commandService.exec(nav.getCurrentPath(), new ShellCommand(GIT_COMMAND, "push", "origin", "develop"));
        publisher.publishEvent(new LocationUpdatedEvent(EventType.FILE_NAVIGATION));
    }

    @ShellMethod("Create a new Release")
    public void gitCreateRelease(@NotNull String name) {
        commandService.exec(nav.getCurrentPath(),
                new ShellCommand(GIT_COMMAND, "checkout", "-b", "release-" + name, "develop"));
        publisher.publishEvent(new LocationUpdatedEvent(EventType.FILE_NAVIGATION));
    }

    @ShellMethod("Terminate a Release")
    public void gitTerminateRelease(@NotNull String name) {
        commandService.exec(nav.getCurrentPath(), new ShellCommand(GIT_COMMAND, "checkout", "develop"));
        commandService.exec(nav.getCurrentPath(), new ShellCommand(GIT_COMMAND, "merge", "--no-f", "name"));
        commandService.exec(nav.getCurrentPath(), new ShellCommand(GIT_COMMAND, "branch", "-d", "name"));
        publisher.publishEvent(new LocationUpdatedEvent(EventType.FILE_NAVIGATION));
    }

    @ShellMethod("Publish a Release to a remote server")
    public void gitPublishRelease(@NotNull String name) {
        commandService.exec(nav.getCurrentPath(), new ShellCommand(GIT_COMMAND, "push", "origin", "develop"));
        publisher.publishEvent(new LocationUpdatedEvent(EventType.FILE_NAVIGATION));
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
