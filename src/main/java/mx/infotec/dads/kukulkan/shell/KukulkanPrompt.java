package mx.infotec.dads.kukulkan.shell;

import static mx.infotec.dads.kukulkan.shell.util.Constants.GIT;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.shell.commands.publishers.LocationUpdatedEvent;
import mx.infotec.dads.kukulkan.shell.domain.Line;
import mx.infotec.dads.kukulkan.shell.domain.Navigator;
import mx.infotec.dads.kukulkan.shell.util.Console;
import mx.infotec.dads.kukulkan.shell.util.FilesCommons;

/**
 * KukulkanPrompt Provided
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Component
public class KukulkanPrompt implements PromptProvider {

    @Autowired
    private Navigator nav;

    private AttributedString prompt = new AttributedString("kukulkan",
            AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));

    private AttributedString basePrompt = new AttributedString("kukulkan",
            AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));

    private AttributedString dirPrompt = new AttributedString("");

    private AttributedString endPrompt = new AttributedString("> ",
            AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));

    @PostConstruct
    private void init() {
        configPrompt();
    }

    @Override
    public AttributedString getPrompt() {
        return prompt;
    }

    @EventListener
    public void handle(LocationUpdatedEvent event) {
        configPrompt();
    }

    private void configPrompt() {
        if (FilesCommons.hasGitFiles(nav.getCurrentPath())) {
            List<Line> result = Console.exec(nav.getCurrentPath(), GIT, (line) -> Optional.ofNullable(new Line(line)),
                    "rev-parse --abbrev-ref HEAD");
            dirPrompt = AttributedString.join(new AttributedString(""),
                    new AttributedString(" @", AttributedStyle.BOLD.foreground(AttributedStyle.YELLOW)),
                    new AttributedString("git/" + result.get(0).getText(),
                            AttributedStyle.BOLD_OFF.foreground(AttributedStyle.YELLOW)));

        } else {
            dirPrompt = new AttributedString("");
        }
        prompt = AttributedString.join(new AttributedString(""), basePrompt, dirPrompt, endPrompt);
    }
}
