package mx.infotec.dads.kukulkan.shell.commands.kukulkan;

import java.util.Date;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Util Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class UtilCommands {

	@ShellMethod("Show the actual date of the system")
	public String date() {
		return new Date().toString();
	}
}
