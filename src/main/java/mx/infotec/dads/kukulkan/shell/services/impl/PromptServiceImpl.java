package mx.infotec.dads.kukulkan.shell.services.impl;

import static mx.infotec.dads.kukulkan.shell.util.Constants.GIT;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.shell.domain.ShellCommand;
import mx.infotec.dads.kukulkan.shell.services.PromptService;
import mx.infotec.dads.kukulkan.shell.util.FilesCommons;

/**
 * PromptFactory, It is used to create a config a prompt
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service
public class PromptServiceImpl implements PromptService {

    @Autowired
    CommandServiceImpl commandService;

    public AttributedString createPrompt(Path currentPath, AttributedString basePrompt, AttributedString endPrompt) {
        AttributedString dirPrompt = null;
        if (FilesCommons.hasGitFiles(currentPath)) {
            List<CharSequence> result = commandService.exec(currentPath,
                    new ShellCommand(GIT).addArg("rev-parse").addArg("--abbrev-ref").addArg("HEAD"),
                    line -> Optional.ofNullable(new AttributedString(line)));

            dirPrompt = AttributedString.join(new AttributedString(""),
                    new AttributedString(" @", AttributedStyle.BOLD.foreground(AttributedStyle.YELLOW)),
                    new AttributedString("git/" + result.get(0).toString(),
                            AttributedStyle.BOLD_OFF.foreground(AttributedStyle.YELLOW)));
        } else {
            dirPrompt = new AttributedString("");
        }
        return AttributedString.join(new AttributedString(""), basePrompt, dirPrompt, endPrompt);
    }
}
