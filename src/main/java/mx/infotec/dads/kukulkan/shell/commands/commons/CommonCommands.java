package mx.infotec.dads.kukulkan.shell.commands.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import mx.infotec.dads.kukulkan.shell.domain.AvailableNativeCommands;
import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.util.Console;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

/**
 * Docker Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class CommonCommands {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonCommands.class);

    @PostConstruct
    public void init() {
        AvailableNativeCommands.getNativeCommands().forEach(command -> {
            testNativeCommand(command);
        });
    }

    @ShellMethod("Show the status of the common commands")
    public void nativeCommands() {
        Console.printf("DOCKER" , Boolean.toString(AvailableNativeCommands.isDockerInstalled()));
        Console.printf("DOCKER COMPOSE" , Boolean.toString(AvailableNativeCommands.isDockerComposeInstalled()));
        Console.printf("GIT" , Boolean.toString(AvailableNativeCommands.isGitInstalled()));
        Console.printf("MAVEN" , Boolean.toString(AvailableNativeCommands.isMavenInstalled()));
    }

    public static void testNativeCommand(NativeCommand nc) {
        StringBuffer output = new StringBuffer();
        try {
            Process p = Runtime.getRuntime().exec(nc.getNativeCommandType().getCommandForVersionInfo());
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            LOGGER.info("[" + nc.getNativeCommandType().getCommand() + "]" + " is installed");
            nc.setInfoMessage(output.toString());
            nc.setActive(true);
        } catch (Exception e) {
            LOGGER.warn("[" + nc.getNativeCommandType().getCommand() + "]" + " is not installed");
            nc.setErrorMessage("You must install the command [" + nc.getNativeCommandType().getCommand() + "]");
        }
    }
}
