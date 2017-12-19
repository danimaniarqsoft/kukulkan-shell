package mx.infotec.dads.kukulkan.shell.domain;

import org.springframework.shell.CompletionProposal;

/**
 * ShellCompletitionProposal
 * 
 * @author Daniel Cortes Pichado
 *
 */
public class ShellCompletionProposal extends CompletionProposal {

    public ShellCompletionProposal(String lineText, String description) {
        super(lineText);
        this.description(description);
    }

    public ShellCompletionProposal(String lineText) {
        super(lineText);
    }
}
