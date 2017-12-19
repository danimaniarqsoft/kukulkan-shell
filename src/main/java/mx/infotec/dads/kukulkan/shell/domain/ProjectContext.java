package mx.infotec.dads.kukulkan.shell.domain;

import java.util.SortedMap;

import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;

/**
 * ProjectContext, It has the main configuration of the Shell
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ProjectContext {

    private ProjectConfiguration project;

    private SortedMap<String, NativeCommand> availableCommands;

    public SortedMap<String, NativeCommand> getAvailableCommands() {
        return availableCommands;
    }

    public void setAvailableCommands(SortedMap<String, NativeCommand> availableCommands) {
        this.availableCommands = availableCommands;
    }

    public ProjectConfiguration getProject() {
        return project;
    }

    public void setProject(ProjectConfiguration project) {
        this.project = project;
    }

}
