package mx.infotec.dads.kukulkan.shell.util;

import java.util.Optional;

/**
 * LineProcessor
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@FunctionalInterface
public interface LineProcessor {
    /**
     * Process a Single Line in the Console exec method
     * 
     * @param line
     * @return
     */
    Optional<String> process(String line);
}
