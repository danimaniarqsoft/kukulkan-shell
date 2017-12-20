package mx.infotec.dads.kukulkan.shell.services;

import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;

/**
 * Main interface to provide NativeCommand to the main handler of commands in
 * the shell.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public interface NativeCommandService {

    public boolean isPresent(NativeCommand cmd);
}
