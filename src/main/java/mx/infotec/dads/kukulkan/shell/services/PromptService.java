package mx.infotec.dads.kukulkan.shell.services;

import static mx.infotec.dads.kukulkan.shell.util.Constants.GIT;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.shell.domain.Line;
import mx.infotec.dads.kukulkan.shell.util.Console;
import mx.infotec.dads.kukulkan.shell.util.FilesCommons;

/**
 * PromptFactory, It is used to create a config a prompt
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service
public class PromptService {

    public AttributedString createPrompt(Path currentPath, AttributedString basePrompt, AttributedString endPrompt) {
        AttributedString dirPrompt = null;
        if (FilesCommons.hasGitFiles(currentPath)) {
            List<Line> result = Console.exec(currentPath, GIT, (line) -> Optional.ofNullable(new Line(line)),
                    "rev-parse --abbrev-ref HEAD");
            dirPrompt = AttributedString.join(new AttributedString(""),
                    new AttributedString(" @", AttributedStyle.BOLD.foreground(AttributedStyle.YELLOW)),
                    new AttributedString("git/" + result.get(0).getText(),
                            AttributedStyle.BOLD_OFF.foreground(AttributedStyle.YELLOW)));

        } else {
            dirPrompt = new AttributedString("");
        }
        return AttributedString.join(new AttributedString(""), basePrompt, dirPrompt, endPrompt);
    }
}
