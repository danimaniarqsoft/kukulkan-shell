package mx.infotec.dads.kukulkan.shell.commands.valueprovided;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProviderSupport;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.engine.util.PKGenerationStrategy;

@Component
public class GenerationTypeProvider extends ValueProviderSupport {
    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext,
            String[] hints) {
        return Arrays.stream(PKGenerationStrategy.values())
                .filter(pkStrategy -> !pkStrategy.equals(PKGenerationStrategy.NULL))
                .map(pkStrategy -> new CompletionProposal(pkStrategy.toString())).collect(Collectors.toList());
    }
}