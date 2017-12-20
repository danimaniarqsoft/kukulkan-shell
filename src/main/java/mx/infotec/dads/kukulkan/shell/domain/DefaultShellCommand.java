package mx.infotec.dads.kukulkan.shell.domain;

/**
 * Native Command show the main commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DefaultShellCommand implements NativeCommand {

    private String command;
    private String testCommand;
    private String infoMessage;
    private String errorMessage;
    private boolean isActive;

    public DefaultShellCommand(String command, String testCommand) {
        this.command = command;
        this.testCommand = testCommand;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String getTestCommand() {
        return this.testCommand;
    }

    public void setTestCommand(String testCommand) {
        this.testCommand = testCommand;
    }

    @Override
    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((command == null) ? 0 : command.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefaultShellCommand other = (DefaultShellCommand) obj;
        if (command == null) {
            if (other.command != null)
                return false;
        } else if (!command.equals(other.command))
            return false;
        return true;
    }

    @Override
    public int compareTo(NativeCommand o) {
        return this.command.compareTo(o.getCommand());
    }
}
