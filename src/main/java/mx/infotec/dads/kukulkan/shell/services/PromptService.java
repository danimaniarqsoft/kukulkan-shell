package mx.infotec.dads.kukulkan.shell.services;

import java.nio.file.Path;

import org.jline.utils.AttributedString;

/**
 * PromptFactory, It is used to create a config a prompt
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public interface PromptService {

    public AttributedString createPrompt(Path currentPath, AttributedString basePrompt, AttributedString endPrompt);
}
