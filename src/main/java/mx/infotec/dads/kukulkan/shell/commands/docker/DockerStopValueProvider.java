package mx.infotec.dads.kukulkan.shell.commands.docker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProviderSupport;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.shell.domain.Line;
import mx.infotec.dads.kukulkan.shell.domain.ShellCommand;
import mx.infotec.dads.kukulkan.shell.domain.ShellCompletionProposal;
import mx.infotec.dads.kukulkan.shell.services.CommandService;
import mx.infotec.dads.kukulkan.shell.util.LineProcessor;
import mx.infotec.dads.kukulkan.shell.util.LineValuedProcessor;

import static mx.infotec.dads.kukulkan.shell.commands.docker.DockerCommands.DOCKER_COMMAND;

/**
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Component
public class DockerStopValueProvider extends ValueProviderSupport {

    @Autowired
    CommandService commandService;

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext,
            String[] hints) {
        LineValuedProcessor processor = (line -> {
            String[] split = line.split("\\s{2,}");
            if (split.length > 0 && !split[0].equals("CONTAINER ID")) {
                return Optional.ofNullable(new Line(split[0], split[6]));
            } else {
                return Optional.empty();
            }
        });
        List<Line> lines = commandService.exec(new ShellCommand(DOCKER_COMMAND).addArg("ps"), processor);
        return lines.stream().map(line -> {
            return new ShellCompletionProposal(line.getText(), line.getDescription());
        }).collect(Collectors.toList());
    }
}