package mx.infotec.dads.kukulkan.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import mx.infotec.dads.kukulkan.engine.util.KukulkanConfigurationProperties;

@ComponentScan(basePackages = { "mx.infotec.dads.kukulkan", "mx.infotec.dads.kukulkan.engine" })
@EnableAutoConfiguration(exclude = { MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class })
@EnableConfigurationProperties({ KukulkanConfigurationProperties.class })
// @EnableMongoRepositories
public class KukulkanShellApplication {

    public static void main(String[] args) {
        SpringApplication.run(KukulkanShellApplication.class, args);
    }
}
