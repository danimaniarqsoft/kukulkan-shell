package mx.infotec.dads.kukulkan.shell.commands.docker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProviderSupport;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.shell.domain.Line;
import mx.infotec.dads.kukulkan.shell.domain.ShellCompletionProposal;
import mx.infotec.dads.kukulkan.shell.util.Console;
import mx.infotec.dads.kukulkan.shell.util.LineProcessor;

import static mx.infotec.dads.kukulkan.shell.util.Constants.DOCKER_COMMAND;

@Component
public class DockerStartValueProvider extends ValueProviderSupport {
    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext,
            String[] hints) {
        LineProcessor processor = (line -> {
            String[] split = line.split("\\s{2,}");
            if (split.length > 0 && !split[0].equals("CONTAINER ID") && split[4].contains("Exited")) {
                return Optional.ofNullable(new Line(split[0], split[5]));
            } else {
                return Optional.empty();
            }
        });
        List<Line> lines = Console.exec(DOCKER_COMMAND, processor, "ps", "-a");
        return lines.stream().map(line -> new ShellCompletionProposal(line.getText(), line.getDescription()))
                .collect(Collectors.toList());
    }
}