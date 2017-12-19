package mx.infotec.dads.kukulkan.shell.config;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.util.PKGenerationStrategy;
import mx.infotec.dads.kukulkan.shell.domain.NativeCommand;
import mx.infotec.dads.kukulkan.shell.domain.ProjectContext;
import mx.infotec.dads.kukulkan.shell.services.NativeCommandProvided;
import mx.infotec.dads.kukulkan.shell.services.NativeCommandService;

@Configuration
public class ProjectContextConfiguration {

    @Bean
    public ProjectContext configProjectContext(List<NativeCommandProvided> nativeCommands,
            NativeCommandService commandService) {
        ProjectContext context = new ProjectContext();
        createDefaultProjectConfiguration(context);
        context.setAvailableCommands(mapDefaultNativeCommands(nativeCommands, commandService));
        return context;
    }

    private SortedMap<String, NativeCommand> mapDefaultNativeCommands(List<NativeCommandProvided> nativeCommands,
            NativeCommandService commandService) {
        SortedMap<String, NativeCommand> cmdSet = new TreeMap<>();
        nativeCommands.forEach(opt -> opt.getNativeCommand().ifPresent(cmd -> {
            commandService.isPresent(cmd);
            cmdSet.put(cmd.getCommand(), cmd);
        }));
        return cmdSet;
    }

    public ProjectContext createDefaultProjectConfiguration(ProjectContext context) {
        ProjectConfiguration pConf = new ProjectConfiguration();
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
        context.setProject(pConf);
        return context;
    }
}
