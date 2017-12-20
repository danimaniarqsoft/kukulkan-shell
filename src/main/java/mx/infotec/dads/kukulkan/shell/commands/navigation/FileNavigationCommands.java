package mx.infotec.dads.kukulkan.shell.commands.navigation;

import static mx.infotec.dads.kukulkan.shell.util.Constants.NULL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.shell.commands.publishers.EventType;
import mx.infotec.dads.kukulkan.shell.commands.publishers.LocationChangeEventPublisher;
import mx.infotec.dads.kukulkan.shell.domain.Navigator;
import mx.infotec.dads.kukulkan.shell.services.CommandService;
import mx.infotec.dads.kukulkan.shell.util.FilesCommons;

/**
 * Docker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class FileNavigationCommands {

    @Autowired
    private Navigator nav;

    @Autowired
    CommandService commandService;

    @Autowired
    private LocationChangeEventPublisher publisher;

    @ShellMethod("Show the current direction")
    public void pwd() {
        commandService.printf(nav.getCurrentPath().toString());
    }

    @ShellMethod(value = "List the currents files", key = { "ls", "ll", "dir" })
    public List<CharSequence> ls() {
        return FilesCommons.showFiles(nav.getCurrentPath());
    }

    @ShellMethod("Change to other location")
    public void cd(@ShellOption(valueProvider = DirectoryValueProvider.class, defaultValue = NULL) String dir) {
        Path newPath = null;
        if ("..".equals(dir)) {
            newPath = nav.getCurrentPath().getParent();
        } else {
            newPath = Paths.get(nav.getCurrentPath().toAbsolutePath().toString(), dir);
        }
        if (newPath.toFile().exists()) {
            nav.setCurrentPath(newPath);
            publisher.publishEvent(EventType.FILE_NAVIGATION);
        } else {
            commandService.printf("The dir does not exist: " + newPath.toString());
        }
    }
}
