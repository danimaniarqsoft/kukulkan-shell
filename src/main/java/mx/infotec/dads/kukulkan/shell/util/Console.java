package mx.infotec.dads.kukulkan.shell.util;

import static mx.infotec.dads.kukulkan.shell.util.AnsiConstants.ANSI_GREEN;
import static mx.infotec.dads.kukulkan.shell.util.AnsiConstants.ANSI_RESET;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.dads.kukulkan.shell.domain.Line;
import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;

/**
 * Useful methods to handle the main Console
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Console {

    private static final Logger LOGGER = LoggerFactory.getLogger(Console.class);

    private Console() {

    }

    public static void printf(String text) {
        System.out.println(AnsiConstants.ANSI_GREEN + text + AnsiConstants.ANSI_RESET);
    }

    public static void printf(String key, String message) {
        System.out.println(String.format(ANSI_GREEN + "[%-15s] -" + ANSI_RESET + "%-30s", key, message));
    }

    public static List<Line> exec(final String command) {
        return exec(command, new String[1]);
    }

    public static List<Line> exec(final String command, LineProcessor processor, String... args) {
        return exec(Paths.get("."), command, processor, args);
    }

    public static List<Line> exec(final String command, String... args) {
        return exec(Paths.get("."), command, line -> {
            printf(line + "\n");
            return Optional.ofNullable(new Line(line));
        }, args);
    }

    public static List<Line> exec(final Path workingDirectory, final String command, String... args) {
        return exec(workingDirectory, command, line -> Optional.ofNullable(new Line(line)), args);
    }

    public static List<Line> exec(final Path workingDirectory, final String command, LineProcessor processor,
            String... args) {
        List<Line> lines = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(command + formatArgs(args), null, workingDirectory.toFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                processor.process(line).ifPresent(lines::add);
            }
        } catch (Exception e) {
            printf("The command [" + command + "]" + " could not be executed");
        }
        return lines;
    }

    private static String formatArgs(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(" ").append(arg);
        }
        return sb.toString();
    }

    public static void testNativeCommand(NativeCommand nc) {
        StringBuilder output = new StringBuilder();
        try {
            Process p = Runtime.getRuntime().exec(nc.getTestCommand());
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            LOGGER.info("[" + nc.getCommand() + "]" + " is installed");
            nc.setInfoMessage(output.toString());
            nc.setActive(true);
        } catch (Exception e) {
            LOGGER.warn("[" + nc.getCommand() + "]" + " is not installed");
            nc.setErrorMessage("You must install the command [" + nc.getCommand() + "]");
        }
    }
}
