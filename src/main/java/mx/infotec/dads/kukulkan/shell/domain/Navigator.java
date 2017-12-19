package mx.infotec.dads.kukulkan.shell.domain;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * FileNavigator
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Component
public class Navigator {

    private Path currentPath;

    private Path previusPath;

    @PostConstruct
    private void init() {
        this.currentPath = Paths.get(System.getProperty("user.dir"));
        this.previusPath = null;
    }

    public Path getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(Path currentPath) {
        this.previusPath= this.currentPath;
        this.currentPath = currentPath;
    }

    public Path getPreviusPath() {
        return previusPath;
    }
}
