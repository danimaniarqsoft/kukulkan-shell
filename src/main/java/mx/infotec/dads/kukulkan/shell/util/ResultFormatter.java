package mx.infotec.dads.kukulkan.shell.util;

import java.util.List;
import java.util.stream.Collectors;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

/**
 * ResultFormatter
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ResultFormatter {
    private ResultFormatter() {
    }

    public static List<AttributedString> formatToGitOutput(List<CharSequence> input) {
        return input.stream().map(result -> {
            if (result.toString().matches("(\\t)(.*)")) {
                return new AttributedString(result.toString(), AttributedStyle.BOLD.foreground(AttributedStyle.RED));
            } else {
                return new AttributedString(result.toString(), AttributedStyle.BOLD.foreground(AttributedStyle.WHITE));
            }
        }).collect(Collectors.toList());

    }
}
