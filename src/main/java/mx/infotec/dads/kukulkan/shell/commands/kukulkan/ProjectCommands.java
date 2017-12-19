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
    private ProjectConfiguration pConf;
    @Autowired
    private LocationChangeEventPublisher event;

    @PostConstruct
    public void initApplication() {

    }

    @ShellMethod("Show the current Project Configuration of the project")
    public void projectShow() {
        printf("ID", pConf.getId());
        printf("Author", pConf.getAuthor());
        printf("DAO Layer", pConf.getDaoLayerName());
        printf("Domain Layer", pConf.getDomainLayerName());
        printf("DTO Layer", pConf.getDtoLayerName());
        printf("Exception Layer", pConf.getExceptionLayerName());
        printf("Key Generation Type", pConf.getGlobalGenerationType().toString());
        printf("Group ID", pConf.getGroupId());
        printf("Packaging", pConf.getPackaging());
        printf("Service Layer", pConf.getServiceLayerName());
        printf("Version", pConf.getVersion());
        printf("Web Layer", pConf.getWebLayerName());
        printf("Year", pConf.getYear());
        event.doStuffAndPublishAnEvent("saludos");
    }

    @ShellMethod("set some property to the ProjectConfiguration Object")
    public void projectSet(@ShellOption(defaultValue = NULL) String id, 
            @ShellOption(defaultValue = NULL) String author,
            @ShellOption(defaultValue = NULL) String daoLayer, 
            @ShellOption(defaultValue = NULL) String domainLayer,
            @ShellOption(defaultValue = NULL) String dtoLayer, 
            @ShellOption(defaultValue = NULL) String exceptionLayer,
            @ShellOption(defaultValue = NULL, valueProvider = GenerationTypeProvider.class) PKGenerationStrategy keyTypeGeneration,
            @ShellOption(defaultValue = NULL) String groupId, 
            @ShellOption(defaultValue = NULL) String packaging,
            @ShellOption(defaultValue = NULL) String serviceLayer, 
            @ShellOption(defaultValue = NULL) String version,
            @ShellOption(defaultValue = NULL) String webLayer, 
            @ShellOption(defaultValue = NULL) String year) {
        if (!NULL.equals(id)) {
            printf("set [ID] to ", id);
            pConf.setId(id);
        }
        if (!NULL.equals(author)) {
            printf("set [author] to ", author);
            pConf.setAuthor(author);
        }
        if (!NULL.equals(daoLayer)) {
            printf("set [daoLayer] to ", daoLayer);
            pConf.setDaoLayerName(daoLayer);
        }
        if (!NULL.equals(domainLayer)) {
            printf("set [domainLayer] to ", domainLayer);
            pConf.setDomainLayerName(domainLayer);
        }
        if (!NULL.equals(dtoLayer)) {
            printf("set [dtoLayer] to ", dtoLayer);
            pConf.setDtoLayerName(dtoLayer);
        }
        if (!NULL.equals(exceptionLayer)) {
            printf("set [exception Layer] to " + exceptionLayer);
            pConf.setExceptionLayerName(exceptionLayer);
        }
        if (!NULL.equals(groupId)) {
            printf("set [groupId] to ", groupId);
            pConf.setGroupId(groupId);
        }
        if (!NULL.equals(packaging)) {
            printf("set [packaging] to ", packaging);
            pConf.setPackaging(packaging);
        }
        if (!NULL.equals(serviceLayer)) {
            printf("set [serviceLayer] to ", serviceLayer);
            pConf.setServiceLayerName(serviceLayer);
        }
        if (!NULL.equals(version)) {
            printf("set [version] to ", version);
            pConf.setVersion(version);
        }
        if (!NULL.equals(webLayer)) {
            printf("set [webLayer] to ", webLayer);
            pConf.setWebLayerName(webLayer);
        }
        if (!NULL.equals(year)) {
            printf("set [year] to ", year);
            pConf.setYear(year);
        }
        if (!keyTypeGeneration.equals(PKGenerationStrategy.NULL)) {
            printf("set [GenerationType] to ", keyTypeGeneration.name());
            pConf.setGlobalGenerationType(keyTypeGeneration);
        }
    }

    @ShellMethod("Set to the default values of the Project Configuration")
    public void projectSetDefaultValues() {
        pConf.setId("kukulkanmongo");
        pConf.setGroupId("mx.infotec.dads.mongo");
        pConf.setVersion("1.0.0");
        pConf.setPackaging("mx.infotec.dads.mongo");
        pConf.setYear("2017");
        pConf.setAuthor("KUKULKAN");
        pConf.setWebLayerName("web.rest");
        pConf.setServiceLayerName("service");
        pConf.setDaoLayerName("repository");
        pConf.setDtoLayerName("dto");
        pConf.setExceptionLayerName("exception");
        pConf.setDomainLayerName("domain");
        pConf.setMongoDb(true);
        pConf.setGlobalGenerationType(PKGenerationStrategy.SEQUENCE);
        printf("ProjectConfiguration reset to:");
        projectShow();
    }
}
