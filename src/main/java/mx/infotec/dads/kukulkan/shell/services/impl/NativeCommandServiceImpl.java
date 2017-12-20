package mx.infotec.dads.kukulkan.shell.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.services.NativeCommandService;

/**
 * Main interface to provide NativeCommand to the main handler of commands in
 * the shell.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service
public class NativeCommandServiceImpl implements NativeCommandService {

    @Autowired
    CommandServiceImpl commandService;

    public boolean isPresent(NativeCommand cmd) {
        commandService.testNativeCommand(cmd);
        return cmd.isActive();
    }
}
