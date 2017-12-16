package mx.infotec.dads.kukulkan.shell.commands.docker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProviderSupport;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.shell.util.Console;
import mx.infotec.dads.kukulkan.shell.util.LineProcessor;

@Component
public class DockerStartValueProvider extends ValueProviderSupport {
    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext,
            String[] hints) {
        LineProcessor processor = (line -> {
            String[] split = line.split("\\s{2,}");
            if (split.length > 0 && !split[0].equals("CONTAINER") && split[4].contains("Exited")) {
                return Optional.ofNullable(split[0]);
            } else {
                return Optional.empty();
            }
        });
        CompletionProposal s = new CompletionProposal("");
        s.value("");

        List<String> lines = Console.exec(DockerCommands.DOCKER_COMMAND, processor, "ps", "-a");
        return lines.stream().map(line -> {
            CompletionProposal sdd= new CompletionProposal(line);
            sdd.description("hola");
            return sdd;
        }).collect(Collectors.toList());
    }
}