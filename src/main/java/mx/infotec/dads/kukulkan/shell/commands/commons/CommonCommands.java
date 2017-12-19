package mx.infotec.dads.kukulkan.shell.commands.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import mx.infotec.dads.kukulkan.shell.domain.ProjectContext;
import mx.infotec.dads.kukulkan.shell.util.Console;

/**
 * Docker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class CommonCommands {

    @Autowired
    ProjectContext context;

    @ShellMethod("Show the status of the common commands")
    public void nativeCommands() {
        context.getAvailableCommands()
                .forEach((key, cmd) -> Console.printf(cmd.getCommand(), Boolean.toString(cmd.isActive())));
    }
}
