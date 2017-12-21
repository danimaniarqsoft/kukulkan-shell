package mx.infotec.dads.kukulkan.shell.util;

import java.util.regex.Pattern;

/**
 * Matcher
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Matcher {

    public static final Pattern INITIAL_TAB = Pattern.compile("(\\t)(.*)");

    private Matcher() {
    }

}
