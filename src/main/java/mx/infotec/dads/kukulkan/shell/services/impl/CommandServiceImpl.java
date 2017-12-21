package mx.infotec.dads.kukulkan.shell.services.impl;

import static mx.infotec.dads.kukulkan.shell.util.AnsiConstants.ANSI_GREEN;
import static mx.infotec.dads.kukulkan.shell.util.AnsiConstants.ANSI_RESET;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.shell.domain.Line;
import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.domain.Navigator;
import mx.infotec.dads.kukulkan.shell.domain.ShellCommand;
import mx.infotec.dads.kukulkan.shell.services.CommandService;
import mx.infotec.dads.kukulkan.shell.util.AnsiConstants;
import mx.infotec.dads.kukulkan.shell.util.LineProcessor;
import mx.infotec.dads.kukulkan.shell.util.LineValuedProcessor;

/**
 * Useful methods to handle the main Console
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service
public class CommandServiceImpl implements CommandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandServiceImpl.class);

    @Autowired
    @Lazy
    Terminal terminal;

    @Autowired
    Navigator nav;

    @Override
    public void printf(String text) {
        terminal.writer().append(AnsiConstants.ANSI_GREEN + text + AnsiConstants.ANSI_RESET);
    }

    @Override
    public void printf(String key, String message) {
        terminal.writer().append(String.format(ANSI_GREEN + "[%-15s] -" + ANSI_RESET + "%-30s", key, message));
    }

    @Override
    public List<CharSequence> exec(final ShellCommand command, LineProcessor processor) {
        return exec(nav.getCurrentPath(), command, processor);
    }

    public List<Line> exec(final ShellCommand command, LineValuedProcessor processor) {
        List<Line> lines = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(command.getExecutableCommand(), null, Paths.get(".").toFile());
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

    @Override
    public List<CharSequence> exec(final ShellCommand command) {
        return exec(nav.getCurrentPath(), command);
    }

    @Override
    public List<CharSequence> exec(final Path workingDirectory, final ShellCommand command) {
        return exec(workingDirectory, command, line -> Optional.ofNullable(new AttributedString(line)));
    }

    @Override
    public List<CharSequence> exec(final Path workingDirectory, final ShellCommand command, LineProcessor processor) {
        List<CharSequence> lines = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(command.getExecutableCommand(), null, workingDirectory.toFile());
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                processor.process(line).ifPresent(lines::add);
            }
        } catch (Exception e) {
            lines.add(e.getMessage());
        }
        return lines;
    }

    @Override
    public void testNativeCommand(NativeCommand nc) {
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
