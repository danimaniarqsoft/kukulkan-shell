package mx.infotec.dads.kukulkan.shell.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.util.PKGenerationStrategy;

@Configuration
public class EntitiesConfiguration {

    private final Logger log = LoggerFactory.getLogger(EntitiesConfiguration.class);

    @Bean
    public ProjectConfiguration validatingMongoEventListener() {
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
        return pConf;
    }
}
