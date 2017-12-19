package mx.infotec.dads.kukulkan.shell.commands.kukulkan;

import static mx.infotec.dads.kukulkan.shell.util.Console.printf;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.util.PKGenerationStrategy;
import mx.infotec.dads.kukulkan.shell.commands.publishers.LocationChangeEventPublisher;
import mx.infotec.dads.kukulkan.shell.commands.valueprovided.GenerationTypeProvider;
import mx.infotec.dads.kukulkan.shell.domain.ProjectContext;

import static mx.infotec.dads.kukulkan.shell.util.Constants.NULL;

/**
 * Util Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class ProjectCommands {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCommands.class);

    @Autowired
    private ProjectContext context;
    @Autowired
    private LocationChangeEventPublisher event;

    @ShellMethod("Show the current Project Configuration of the project")
    public void projectShow() {
        printf("ID", context.getProject().getId());
        printf("Author", context.getProject().getAuthor());
        printf("DAO Layer", context.getProject().getDaoLayerName());
        printf("Domain Layer", context.getProject().getDomainLayerName());
        printf("DTO Layer", context.getProject().getDtoLayerName());
        printf("Exception Layer", context.getProject().getExceptionLayerName());
        printf("Key Generation Type", context.getProject().getGlobalGenerationType().toString());
        printf("Group ID", context.getProject().getGroupId());
        printf("Packaging", context.getProject().getPackaging());
        printf("Service Layer", context.getProject().getServiceLayerName());
        printf("Version", context.getProject().getVersion());
        printf("Web Layer", context.getProject().getWebLayerName());
        printf("Year", context.getProject().getYear());
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
            printf("set [ID] to ", id);
            context.getProject().setId(id);
        }
        if (!NULL.equals(author)) {
            printf("set [author] to ", author);
            context.getProject().setAuthor(author);
        }
        if (!NULL.equals(daoLayer)) {
            printf("set [daoLayer] to ", daoLayer);
            context.getProject().setDaoLayerName(daoLayer);
        }
        if (!NULL.equals(domainLayer)) {
            printf("set [domainLayer] to ", domainLayer);
            context.getProject().setDomainLayerName(domainLayer);
        }
        if (!NULL.equals(dtoLayer)) {
            printf("set [dtoLayer] to ", dtoLayer);
            context.getProject().setDtoLayerName(dtoLayer);
        }
        if (!NULL.equals(exceptionLayer)) {
            printf("set [exception Layer] to " + exceptionLayer);
            context.getProject().setExceptionLayerName(exceptionLayer);
        }
        if (!NULL.equals(groupId)) {
            printf("set [groupId] to ", groupId);
            context.getProject().setGroupId(groupId);
        }
        if (!NULL.equals(packaging)) {
            printf("set [packaging] to ", packaging);
            context.getProject().setPackaging(packaging);
        }
        if (!NULL.equals(serviceLayer)) {
            printf("set [serviceLayer] to ", serviceLayer);
            context.getProject().setServiceLayerName(serviceLayer);
        }
        if (!NULL.equals(version)) {
            printf("set [version] to ", version);
            context.getProject().setVersion(version);
        }
        if (!NULL.equals(webLayer)) {
            printf("set [webLayer] to ", webLayer);
            context.getProject().setWebLayerName(webLayer);
        }
        if (!NULL.equals(year)) {
            printf("set [year] to ", year);
            context.getProject().setYear(year);
        }
        if (!keyTypeGeneration.equals(PKGenerationStrategy.NULL)) {
            printf("set [GenerationType] to ", keyTypeGeneration.name());
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
        printf("ProjectConfiguration reset to:");
        projectShow();
    }
}
