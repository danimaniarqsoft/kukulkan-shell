package mx.infotec.dads.kukulkan.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import mx.infotec.dads.kukulkan.shell.domain.Navigator;

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

    private AttributedString prompt = new AttributedString("kukulkan> \n> ",
            AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));

    @Override
    public AttributedString getPrompt() {
        return prompt;
    }

    @EventListener
    public void handle(LocationUpdatedEvent event) {
        prompt = new AttributedString("kukulkan >" + nav.getCurrentPath().toFile().getAbsolutePath(),
                AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
