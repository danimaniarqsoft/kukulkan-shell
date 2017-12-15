package mx.infotec.dads.kukulkan.shell.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import mx.infotec.dads.kukulkan.engine.domain.core.ArchetypeType;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModel;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaDomainModel;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.domain.core.Rule;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;
import mx.infotec.dads.kukulkan.engine.factories.LayerTaskFactory;
import mx.infotec.dads.kukulkan.engine.grammar.GrammarMapping;
import mx.infotec.dads.kukulkan.engine.grammar.KukulkanVisitor;
import mx.infotec.dads.kukulkan.engine.repository.RuleRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleTypeRepository;
import mx.infotec.dads.kukulkan.engine.service.GenerationService;
import mx.infotec.dads.kukulkan.engine.util.FileUtil;
import mx.infotec.dads.kukulkan.engine.util.InflectorProcessor;
import mx.infotec.dads.kukulkan.engine.util.KukulkanConfigurationProperties;
import mx.infotec.dads.kukulkan.engine.util.PKGenerationStrategy;
import mx.infotec.dads.kukulkan.shell.util.Console;

/**
 * Util Commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@ShellComponent
public class KukulkanGeneration {

    @Autowired
    private GenerationService generationService;
    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private LayerTaskFactory layerTaskFactory;
    @Autowired
    private RuleTypeRepository ruleTypeRepository;

    @Autowired
    private KukulkanConfigurationProperties prop;

    @ShellMethod("Show the current output dir")
    public String outputdir() {
        return prop.getConfig().getOutputdir();
    }
    
    
    @ShellMethod("Show the project properties")
    public String showProjectProperties() {
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
        pConf.setDomainLayerName("domain");
        pConf.setMongoDb(true);
        pConf.setGlobalGenerationType(PKGenerationStrategy.SEQUENCE);
        Console.printf(pConf.getId());
        Console.printf(pConf.getGroupId());
        Console.printf(pConf.getVersion());
        Console.printf(pConf.getPackaging());
        Console.printf(pConf.getYear());
        Console.printf(pConf.getAuthor());
        Console.printf(pConf.getWebLayerName());
        Console.printf(pConf.getServiceLayerName());
        Console.printf(pConf.getDaoLayerName());
        Console.printf(pConf.getDomainLayerName());
        Console.printf(Boolean.toString(pConf.isMongoDb()));
        Console.printf(pConf.getGlobalGenerationType().name());
        return "";
    }
    
    @ShellMethod("Create an App")
    public String createApp() {
        Console.printf("Generating app");
        Rule rule = new Rule();
        RuleType ruleType = ruleTypeRepository.findAll().get(0);
        ruleType.setName("singular");
        rule.setRuleType(ruleType);
        Example<Rule> ruleExample = Example.of(rule);
        List<Rule> rulesList = ruleRepository.findAll(ruleExample);
        for (Rule item : rulesList) {
            InflectorProcessor.getInstance().addSingularize(item.getExpression(), item.getReplacement());
        }
        Console.printf("Preparing Project Configuration");
        // Create ProjectConfiguration
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
        pConf.setDomainLayerName("domain");
        pConf.setMongoDb(true);
        pConf.setGlobalGenerationType(PKGenerationStrategy.SEQUENCE);
        // Create DataStore

        // Create DataModel
        DomainModel dataModel = new JavaDomainModel();
        KukulkanVisitor semanticAnalyzer = new KukulkanVisitor();

        // Mapping DataContext into DataModel
        List<DomainModelGroup> dmgList = GrammarMapping.createSingleDataModelGroupList(semanticAnalyzer);
        dataModel.setDomainModelGroup(dmgList);
        // Create GeneratorContext
        GeneratorContext genCtx = new GeneratorContext(dataModel, pConf);
        // Process Activities
        Console.printf("Processing model");
        generationService.process(genCtx, layerTaskFactory.getLayerTaskSet(ArchetypeType.ANGULAR_SPRING));
        Console.printf("Savint file");
        FileUtil.saveToFile(genCtx);
        // System.out.println(Paths.get(prop.getOutputdir() + "/" +
        // pConf.getId()));
        // FileUtil.createZip(Paths.get(prop.getConfig().getOutputdir() + "/" +
        // pConf.getId()), "physicalArchitecture");
        return "Success!";
    }
}
