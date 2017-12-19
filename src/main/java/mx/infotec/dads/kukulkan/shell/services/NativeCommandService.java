package mx.infotec.dads.kukulkan.shell.services;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.util.Console;

/**
 * Main interface to provide NativeCommand to the main handler of commands in
 * the shell.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service
public class NativeCommandService implements Serializable {

    private static final long serialVersionUID = 1L;

    public boolean isPresent(NativeCommand cmd) {
        Console.testNativeCommand(cmd);
        return cmd.isActive();
    }
}
