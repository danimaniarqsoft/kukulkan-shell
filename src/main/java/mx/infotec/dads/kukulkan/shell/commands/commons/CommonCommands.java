package mx.infotec.dads.kukulkan.shell.commands.commons;

import java.util.ArrayList;
import java.util.List;

import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import mx.infotec.dads.kukulkan.shell.domain.ProjectContext;
import mx.infotec.dads.kukulkan.shell.services.CommandService;

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
    @Autowired
    @Lazy
    Terminal terminal;

    @Autowired
    CommandService commandService;

    @ShellMethod("Show the status of the common commands")
    public void nativeCommands() {
        context.getAvailableCommands()
                .forEach((key, cmd) -> commandService.printf(cmd.getCommand(), Boolean.toString(cmd.isActive())));
    }

    @ShellMethod("Show the status of the common commands")
    public List<CharSequence> test() {
        List<CharSequence> sequence = new ArrayList<>();
        AttributedStringBuilder result = new AttributedStringBuilder();
        result.append("AVAILABLE COMMANDS\n\n", AttributedStyle.DEFAULT.underline());
        terminal.writer().append("Hola mundo");
        sequence.add("hola");
        sequence.add("mundo");
        return sequence;
    }
}
