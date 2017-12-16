package mx.infotec.dads.kukulkan.shell.util;

import static mx.infotec.dads.kukulkan.shell.util.AnsiConstants.ANSI_GREEN;
import static mx.infotec.dads.kukulkan.shell.util.AnsiConstants.ANSI_RESET;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Useful methods to handle the main Console
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Console {

    private Console() {

    }

    public static void printf(String text) {
        System.out.println(AnsiConstants.ANSI_GREEN + text + AnsiConstants.ANSI_RESET);
    }

    public static void printf(String key, String message) {
        System.out.println(String.format(ANSI_GREEN + "[%-15s] -" + ANSI_RESET + "%-30s", key, message));
    }

    public static void exec(final String command, String... args) {
        exec(command, (s) -> {
            printf(s + "\n");
            return Optional.ofNullable(s);
        }, args);
    }

    public static List<String> exec(final String command, LineProcessor processor, String... args) {
        List<String> lines = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(command + formatArgs(args));
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                processor.process(line).ifPresent(result -> lines.add(result));
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
}
