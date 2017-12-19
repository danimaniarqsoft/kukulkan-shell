package mx.infotec.dads.kukulkan.shell.commands.navigation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProviderSupport;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.shell.domain.Navigator;
import mx.infotec.dads.kukulkan.shell.util.FilesCommons;

@Component
public class DirectoryValueProvider extends ValueProviderSupport {

    @Autowired
    private Navigator nav;

    @Override
    public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext,
            String[] hints) {
        return FilesCommons.filterDirs(nav.getCurrentPath());
    }
}