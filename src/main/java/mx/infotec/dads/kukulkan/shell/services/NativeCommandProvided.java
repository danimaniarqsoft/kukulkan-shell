package mx.infotec.dads.kukulkan.shell.services;

import java.util.Optional;

import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;

/**
 * Main interface to provide NativeCommand to the main handler of commands in
 * the shell.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public interface NativeCommandProvided {

    Optional<NativeCommand> getNativeCommand();

}
