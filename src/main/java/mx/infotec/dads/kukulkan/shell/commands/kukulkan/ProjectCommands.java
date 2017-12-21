package mx.infotec.dads.kukulkan.shell.commands.kukulkan;

import static mx.infotec.dads.kukulkan.shell.util.Constants.NULL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.engine.util.PKGenerationStrategy;
import mx.infotec.dads.kukulkan.shell.commands.valueprovided.GenerationTypeProvider;
import mx.infotec.dads.kukulkan.shell.domain.ProjectContext;
import mx.infotec.dads.kukulkan.shell.services.CommandService;

/**
 * Util Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class ProjectCommands {

    @Autowired
    CommandService commandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCommands.class);

    @Autowired
    private ProjectContext context;

    @ShellMethod("Show the current Project Configuration of the project")
    public void projectShow() {
        commandService.printf("ID", context.getProject().getId());
        commandService.printf("Author", context.getProject().getAuthor());
        commandService.printf("DAO Layer", context.getProject().getDaoLayerName());
        commandService.printf("Domain Layer", context.getProject().getDomainLayerName());
        commandService.printf("DTO Layer", context.getProject().getDtoLayerName());
        commandService.printf("Exception Layer", context.getProject().getExceptionLayerName());
        commandService.printf("Key Generation Type", context.getProject().getGlobalGenerationType().toString());
        commandService.printf("Group ID", context.getProject().getGroupId());
        commandService.printf("Packaging", context.getProject().getPackaging());
        commandService.printf("Service Layer", context.getProject().getServiceLayerName());
        commandService.printf("Version", context.getProject().getVersion());
        commandService.printf("Web Layer", context.getProject().getWebLayerName());
        commandService.printf("Year", context.getProject().getYear());
    }

    @ShellMethod("set some property to the ProjectConfiguration Object")
    public void projectSet(@ShellOption(defaultValue = NULL) String id, @ShellOption(defaultValue = NULL) String author,
            @ShellOption(defaultValue = NULL) String daoLayer, @ShellOption(defaultValue = NULL) String domainLayer,
            @ShellOption(defaultValue = NULL) String dtoLayer, @ShellOption(defaultValue = NULL) String exceptionLayer,
            @ShellOption(defaultValue = NULL, valueProvider = GenerationTypeProvider.class) PKGenerationStrategy keyTypeGeneration,
            @ShellOption(defaultValue = NULL) String groupId, @ShellOption(defaultValue = NULL) String packaging,
            @ShellOption(defaultValue = NULL) String serviceLayer, @ShellOption(defaultValue = NULL) String version,
            @ShellOption(defaultValue = NULL) String webLayer, @ShellOption(defaultValue = NULL) String year) {
        if (!NULL.equals(id)) {
            commandService.printf("set [ID] to ", id);
            context.getProject().setId(id);
        }
        if (!NULL.equals(author)) {
            commandService.printf("set [author] to ", author);
            context.getProject().setAuthor(author);
        }
        if (!NULL.equals(daoLayer)) {
            commandService.printf("set [daoLayer] to ", daoLayer);
            context.getProject().setDaoLayerName(daoLayer);
        }
        if (!NULL.equals(domainLayer)) {
            commandService.printf("set [domainLayer] to ", domainLayer);
            context.getProject().setDomainLayerName(domainLayer);
        }
        if (!NULL.equals(dtoLayer)) {
            commandService.printf("set [dtoLayer] to ", dtoLayer);
            context.getProject().setDtoLayerName(dtoLayer);
        }
        if (!NULL.equals(exceptionLayer)) {
            commandService.printf("set [exception Layer] to " + exceptionLayer);
            context.getProject().setExceptionLayerName(exceptionLayer);
        }
        if (!NULL.equals(groupId)) {
            commandService.printf("set [groupId] to ", groupId);
            context.getProject().setGroupId(groupId);
        }
        if (!NULL.equals(packaging)) {
            commandService.printf("set [packaging] to ", packaging);
            context.getProject().setPackaging(packaging);
        }
        if (!NULL.equals(serviceLayer)) {
            commandService.printf("set [serviceLayer] to ", serviceLayer);
            context.getProject().setServiceLayerName(serviceLayer);
        }
        if (!NULL.equals(version)) {
            commandService.printf("set [version] to ", version);
            context.getProject().setVersion(version);
        }
        if (!NULL.equals(webLayer)) {
            commandService.printf("set [webLayer] to ", webLayer);
            context.getProject().setWebLayerName(webLayer);
        }
        if (!NULL.equals(year)) {
            commandService.printf("set [year] to ", year);
            context.getProject().setYear(year);
        }
        if (!keyTypeGeneration.equals(PKGenerationStrategy.NULL)) {
            commandService.printf("set [GenerationType] to ", keyTypeGeneration.name());
            context.getProject().setGlobalGenerationType(keyTypeGeneration);
        }
    }

    @ShellMethod("Set to the default values of the Project Configuration")
    public void projectSetDefaultValues() {
        context.getProject().setId("kukulkanmongo");
        context.getProject().setGroupId("mx.infotec.dads.mongo");
        context.getProject().setVersion("1.0.0");
        context.getProject().setPackaging("mx.infotec.dads.mongo");
        context.getProject().setYear("2017");
        context.getProject().setAuthor("KUKULKAN");
        context.getProject().setWebLayerName("web.rest");
        context.getProject().setServiceLayerName("service");
        context.getProject().setDaoLayerName("repository");
        context.getProject().setDtoLayerName("dto");
        context.getProject().setExceptionLayerName("exception");
        context.getProject().setDomainLayerName("domain");
        context.getProject().setMongoDb(true);
        context.getProject().setGlobalGenerationType(PKGenerationStrategy.SEQUENCE);
        commandService.printf("ProjectConfiguration reset to:");
        projectShow();
    }
}
