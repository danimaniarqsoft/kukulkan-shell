package mx.infotec.dads.kukulkan.shell.commands.navigation;

import static mx.infotec.dads.kukulkan.shell.util.FilesCommons.showFiles;
import static mx.infotec.dads.kukulkan.shell.util.ResultFormatter.formatDirNotExistText;
import static mx.infotec.dads.kukulkan.shell.util.ResultFormatter.formatNormalText;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.jline.utils.AttributedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.shell.commands.publishers.EventType;
import mx.infotec.dads.kukulkan.shell.commands.publishers.LocationUpdatedEvent;
import mx.infotec.dads.kukulkan.shell.domain.Navigator;
import mx.infotec.dads.kukulkan.shell.services.CommandService;

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
    private ApplicationEventPublisher publisher;

    @ShellMethod("Show the current direction")
    public AttributedString pwd() {
        return formatNormalText(nav.getCurrentPath().toString());
    }

    @ShellMethod(value = "List the currents files", key = { "ls", "ll", "dir" })
    public List<AttributedString> ls() {
        return showFiles(nav.getCurrentPath());
    }

    @ShellMethod("Change to other location")
    public AttributedString cd(@ShellOption(valueProvider = DirectoryValueProvider.class) @NotNull String dir) {
        Path toChange = Paths.get(dir);
        Path newPath = getNewPath(toChange);
        return validateNewPath(newPath);
    }

    private AttributedString validateNewPath(Path newPath) {
        if (newPath.toFile().exists()) {
            nav.setCurrentPath(newPath);
            publisher.publishEvent(new LocationUpdatedEvent(EventType.FILE_NAVIGATION));
            return formatNormalText(newPath.toString());
        } else {
            return formatDirNotExistText(newPath.toString());
        }
    }

    private Path getNewPath(Path toChange) {
        Path newPath;
        if ("..".equals(toChange.toString())) {
            newPath = nav.getCurrentPath().getParent();
        } else if (toChange.isAbsolute()) {
            newPath = toChange;
        } else {
            newPath = Paths.get(nav.getCurrentPath().toAbsolutePath().toString(), toChange.toString());
        }
        return newPath;
    }
}
