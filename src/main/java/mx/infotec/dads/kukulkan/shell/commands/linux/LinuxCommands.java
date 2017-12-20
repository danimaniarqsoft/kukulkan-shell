package mx.infotec.dads.kukulkan.shell.commands.linux;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.result.ThrowableResultHandler;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.shell.services.CommandService;
import mx.infotec.dads.kukulkan.shell.util.AnsiConstants;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Util Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class LinuxCommands {

    @Autowired
    CommandService commandService;
    
    @Autowired
    private ThrowableResultHandler throwableResultHandler;

    @ShellMethod("make a ping to a host")
    public String ping(String host) {
        commandService.printf("todo bien");
        commandService.printf(AnsiConstants.ANSI_BLUE, "blue");
        commandService.printf(AnsiConstants.ANSI_BLACK, "black");
        commandService.printf(AnsiConstants.ANSI_CYAN, "cyan");
        commandService.printf(AnsiConstants.ANSI_GRAY, "gray");
        commandService.printf(AnsiConstants.ANSI_GREEN, "green");
        commandService.printf(AnsiConstants.ANSI_PURPLE, "purple");
        commandService.printf(AnsiConstants.ANSI_RED, "red");
        commandService.printf(AnsiConstants.ANSI_WHITE, "white");
        commandService.printf(AnsiConstants.ANSI_YELLOW, "yellow");

        String command = "ping -c 3 " + host;
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
}
