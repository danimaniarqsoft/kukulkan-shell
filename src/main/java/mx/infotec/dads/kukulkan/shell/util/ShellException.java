package mx.infotec.dads.kukulkan.shell.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ShellException
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ShellException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final List<String> words;

    private final ExceptionType type;

    public ShellException(List<String> words) {
        this.words = words;
        type = ExceptionType.ERROR;
    }

    public ShellException(ExceptionType type, List<String> words) {
        this.words = words;
        this.type = type;
    }

    public ShellException(String... messages) {
        words = new ArrayList<>();
        type = ExceptionType.ERROR;
        Collections.addAll(words, messages);
    }

    public ShellException(ExceptionType type, String... messages) {
        words = new ArrayList<>();
        this.type = type;
        Collections.addAll(words, messages);
    }

    @Override
    public String getMessage() {
        return String.format("Kukulkan error: '%s'", words.stream().collect(Collectors.joining(" ")));
    }

    public ExceptionType getType() {
        return type;
    }

}
