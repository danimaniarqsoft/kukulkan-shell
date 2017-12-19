package mx.infotec.dads.kukulkan.shell;

import javax.annotation.PostConstruct;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.shell.commands.publishers.LocationUpdatedEvent;
import mx.infotec.dads.kukulkan.shell.domain.Navigator;
import mx.infotec.dads.kukulkan.shell.services.PromptService;

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

    @Autowired
    private PromptService promptService;

    private AttributedString prompt = new AttributedString("kukulkan",
            AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));

    private AttributedString basePrompt = new AttributedString("kukulkan",
            AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));

    private AttributedString endPrompt = new AttributedString("> ",
            AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));

    @PostConstruct
    private void init() {
        prompt = promptService.createPrompt(nav.getCurrentPath(), basePrompt, endPrompt);
    }

    @Override
    public AttributedString getPrompt() {
        return prompt;
    }

    @EventListener
    public void handle(LocationUpdatedEvent event) {
        prompt = promptService.createPrompt(nav.getCurrentPath(), basePrompt, endPrompt);
    }

}
