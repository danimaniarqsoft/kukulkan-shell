package mx.infotec.dads.kukulkan.shell.commands.commons;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import mx.infotec.dads.kukulkan.shell.domain.AboutInfo;
import mx.infotec.dads.kukulkan.shell.domain.ProjectContext;
import mx.infotec.dads.kukulkan.shell.domain.ShellCommand;
import mx.infotec.dads.kukulkan.shell.handlers.ShellResultHandler;
import mx.infotec.dads.kukulkan.shell.services.CommandService;
import mx.infotec.dads.kukulkan.shell.util.ExceptionType;
import mx.infotec.dads.kukulkan.shell.util.ShellException;

/**
 * Docker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class CommonCommands {

    @Autowired
    private ProjectContext context;
    @Autowired
    @Lazy
    private Terminal terminal;

    @Autowired
    private ShellResultHandler resultHandler;

    @Autowired
    CommandService commandService;

    @ShellMethod("Show the status of the common commands")
    public void nativeCommands() {
        context.getAvailableCommands()
                .forEach((key, cmd) -> commandService.printf(cmd.getCommand(), Boolean.toString(cmd.isActive())));
    }

    @ShellMethod("Show the status of the common commands")
    public AboutInfo test() {
        AboutInfo info = new AboutInfo("server", "102");
        return info;
    }

    @ShellMethod("Run a native Command")
    public List<CharSequence> nativeCmd(@NotNull String cmd) {
        return commandService.exec(new ShellCommand(cmd));
    }

}
