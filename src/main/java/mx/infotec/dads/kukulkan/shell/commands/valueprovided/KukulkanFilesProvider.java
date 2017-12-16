package mx.infotec.dads.kukulkan.shell.commands.valueprovided;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProviderSupport;
import org.springframework.stereotype.Component;

@Component
public class KukulkanFilesProvider extends ValueProviderSupport {
    private static final String KUKULKAN_FILE_PATTERN = "^[a-zA-Z0-9]*\\.3k$";
    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext,
            String[] hints) {
        try {
            return Files.list(Paths.get("."))
                    .filter(path -> path.getFileName().toString().matches(KUKULKAN_FILE_PATTERN))
                    .map(path -> new CompletionProposal(path.getFileName().toString()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            List<CompletionProposal> defaultList = new ArrayList<>();
            defaultList.add(new CompletionProposal("[No Files Found with .3K extension]"));
            return defaultList;
        }
    }
}