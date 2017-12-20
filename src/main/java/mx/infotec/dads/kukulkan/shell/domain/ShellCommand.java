package mx.infotec.dads.kukulkan.shell.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Native Command show the main commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ShellCommand implements Command {

    private String command;

    private List<Args> args = new ArrayList<>();

    public ShellCommand(String command) {
        this.command = command;
    }

    public ShellCommand(String command, String... args) {
        this.command = command;
        for (String arg : args) {
            this.args.add(new Args(arg));
        }
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ShellCommand addArg(String arg) {
        this.args.add(new Args(arg));
        return this;
    }

    public String getArgs() {
        StringBuilder sb = new StringBuilder();
        for (Args arg : args) {
            sb.append(" ").append(arg.getParam());
        }
        return sb.toString();

    }

    public String getExecutableCommand() {
        StringBuilder sb = new StringBuilder(command);
        for (Args arg : args) {
            sb.append(" ").append(arg.getParam());
        }
        return sb.toString();

    }
}
