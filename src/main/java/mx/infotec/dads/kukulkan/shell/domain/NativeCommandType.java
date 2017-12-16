package mx.infotec.dads.kukulkan.shell.domain;

/**
 * NativeCommandType
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public enum NativeCommandType {
    DOCKER("docker","version"), DOCKER_COMPOSE("docker-compose","version"), GIT("git","version"), MAVEN("mvn","-v");

    private NativeCommandType(String nativeCommand, String execForInfo) {
        this.nativeCommand = nativeCommand;
        this.versionParam = execForInfo;
    }

    private String nativeCommand;
    private String versionParam;

    public String getCommand() {
        return this.nativeCommand;
    }

    public String getCommandForVersionInfo() {
        return this.nativeCommand+" "+this.versionParam;
    }
}
