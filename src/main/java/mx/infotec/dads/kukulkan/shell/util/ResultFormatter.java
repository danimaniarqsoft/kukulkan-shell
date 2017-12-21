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

    /**
     * format a List<CharSequence> in order to highlight some git command
     * results
     * 
     * @param input
     * @return List<AttributedString>
     */
    public static List<AttributedString> formatToGitOutput(List<CharSequence> input) {
        return input.stream().map(result -> {
            if (Matcher.INITIAL_TAB.matcher(result.toString()).find()) {
                return new AttributedString(result.toString(), AttributedStyle.BOLD.foreground(AttributedStyle.RED));
            } else {
                return new AttributedString(result.toString(), AttributedStyle.BOLD.foreground(AttributedStyle.WHITE));
            }
        }).collect(Collectors.toList());

    }

    public static AttributedString formatNormalText(String text) {
        return new AttributedString(text, AttributedStyle.BOLD.blinkOff().foreground(AttributedStyle.WHITE));
    }

    public static AttributedString formatDirNotExistText(String text) {
        return formatErrorText("The dir does not exist: " + text);
    }

    public static AttributedString formatErrorText(String text) {
        return new AttributedString(text, AttributedStyle.BOLD.foreground(AttributedStyle.RED));
    }

    public static AttributedString defaulBasePrompt() {
        return new AttributedString("kukulkan", AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));
    }

    public static AttributedString defaulEndPrompt() {
        return new AttributedString("> ", AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));
    }
}
