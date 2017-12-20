package mx.infotec.dads.kukulkan.shell.domain;

/**
 * Native Command show the main commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public interface Command {

    /**
     * Return the command to be executed
     * 
     * @return the command
     */
    public String getCommand();

}
