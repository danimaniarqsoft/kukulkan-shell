package mx.infotec.dads.kukulkan.shell.commands.kukulkan;

import java.util.concurrent.TimeUnit;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Util Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class KukulkanInfo {

    @Autowired
    @Lazy
    private Terminal terminal;

    @ShellMethod("Show the version of Kukulkan Shell")
    public String info() {
        return "v1.0.0";
    }

    @ShellMethod("Show a list of supported archetypes")
    public String archetypes() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append("Angular 4 - Spring - JPA").append("\n");
        sb.append("Angular 4 - Spring - Mongo").append("\n");
        sb.append("Angular 1.5.8 - Spring - JPA").append("\n");
        sb.append("Angular 1.5.8 - Spring - Mongo").append("\n");
        return sb.toString();
    }

    @ShellMethod("Show the copyright")
    public String copyright() {
        StringBuilder sb = new StringBuilder();
        sb.append("@CONACYT - INFOTEC").append("\n");
        return sb.toString();
    }

    @ShellMethod("Show the contact information")
    public String contact() {
        StringBuilder sb = new StringBuilder();
        sb.append("Daniel Cortes Pichardo").append("\n");
        sb.append("daniel.cortes@infotec.mx").append("\n");
        sb.append("https://github.com/danimaniarqsoft/kukulkan-shell");
        return sb.toString();
    }
}
