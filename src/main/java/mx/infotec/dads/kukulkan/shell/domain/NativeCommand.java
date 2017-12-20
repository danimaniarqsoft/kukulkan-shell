package mx.infotec.dads.kukulkan.shell.domain;

/**
 * Native Command show the main commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public interface NativeCommand extends Command, Comparable<NativeCommand> {

    /**
     * Return the command to be executed
     * 
     * @return the command
     */
    public String getCommand();

    /**
     * Return the command used for test if the command is presented, In the test
     * process when the testCommand is used, it must return true if the command
     * is installed in the current machine, false otherwise.
     * 
     * @return
     */
    public String getTestCommand();

    /**
     * Get a useful info message to show in the console
     * 
     * @return
     */
    public String getInfoMessage();

    /**
     * Get a useful info message to show in the console
     * 
     * @return
     */
    public void setInfoMessage(String message);

    /**
     * Return a message if the command is not installed
     * 
     * @return
     */
    public String getErrorMessage();

    /**
     * Return a message if the command is not installed
     * 
     * @return
     */
    public void setErrorMessage(String message);

    /**
     * Return true if the command is installed, false otherwise
     * 
     * @return boolean
     */
    public boolean isActive();

    /**
     * Set true if the command is installed in the machine, false otherwise.
     *
     */
    public void setActive(boolean active);
}
