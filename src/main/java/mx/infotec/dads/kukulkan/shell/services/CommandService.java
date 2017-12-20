package mx.infotec.dads.kukulkan.shell.services;

import java.nio.file.Path;
import java.util.List;

import mx.infotec.dads.kukulkan.shell.domain.Line;
import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.domain.ShellCommand;
import mx.infotec.dads.kukulkan.shell.util.LineProcessor;

/**
 * Useful methods to handle the main Console
 * 
 * @author Daniel Cortes Pichardo
 *
 */

public interface CommandService {

    public void printf(String text);

    public void printf(String key, String message);

    public List<Line> exec(final ShellCommand command);

    public List<Line> exec(final ShellCommand command, LineProcessor processor);

    public List<Line> exec(final Path workingDirectory, final ShellCommand command);

    public List<Line> exec(final Path workingDirectory, final ShellCommand command, LineProcessor processor);

    public void testNativeCommand(NativeCommand nc);
}
