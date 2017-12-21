package mx.infotec.dads.kukulkan.shell.handlers;

import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.result.TerminalAwareResultHandler;

import mx.infotec.dads.kukulkan.shell.util.ShellException;

/**
 * ShellResultHandler
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ShellResultHandler extends TerminalAwareResultHandler<ShellException> {

    @Override
    protected void doHandleResult(ShellException result) {
        AttributedStringBuilder attrBuilder = new AttributedStringBuilder();
        attrBuilder.append(result.getType().getTitle(), result.getType().getStyle());
        attrBuilder.append(result.getMessage(), AttributedStyle.BOLD.foreground(AttributedStyle.WHITE));
        attrBuilder.append("");
        terminal.writer().println(attrBuilder.toAnsi());
        terminal.flush();
    }
}
