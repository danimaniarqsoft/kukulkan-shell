package mx.infotec.dads.kukulkan.shell.commands.kukulkan;

import java.io.File;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = "-file", description = "File with 3k extension")
    private File file;

    
    @Override
    public String toString() {
        return "Args{" + "parameters=" + getFile() + '}';
    }


    public File getFile() {
        return file;
    }
}