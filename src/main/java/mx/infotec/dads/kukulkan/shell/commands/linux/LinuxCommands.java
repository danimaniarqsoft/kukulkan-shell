package mx.infotec.dads.kukulkan.shell.commands.linux;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.shell.util.AnsiConstants;
import mx.infotec.dads.kukulkan.shell.util.Console;

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

    @ShellMethod("make a ping to a host")
    public String ping(String host) {
        Console.printf("todo bien");
        Console.printf(AnsiConstants.ANSI_BLUE, "blue");
        Console.printf(AnsiConstants.ANSI_BLACK, "black");
        Console.printf(AnsiConstants.ANSI_CYAN, "cyan");
        Console.printf(AnsiConstants.ANSI_GRAY, "gray");
        Console.printf(AnsiConstants.ANSI_GREEN, "green");
        Console.printf(AnsiConstants.ANSI_PURPLE, "purple");
        Console.printf(AnsiConstants.ANSI_RED, "red");
        Console.printf(AnsiConstants.ANSI_WHITE, "white");
        Console.printf(AnsiConstants.ANSI_YELLOW, "yellow");

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
