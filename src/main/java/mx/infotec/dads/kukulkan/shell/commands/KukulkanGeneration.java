package mx.infotec.dads.kukulkan.shell.commands;

import javax.validation.Valid;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Util Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class KukulkanGeneration {

	@ShellMethod("Bind parameters to JCommander POJO.")
	public String jcommander(@ShellOption(optOut = true) @Valid Args args) {
		return "You said " + args;
	}
}
