package mx.infotec.dads.kukulkan.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

/**
 * KukulkanPrompt Provided
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Component
public class KukulkanPrompt implements PromptProvider {

	@Override
	public AttributedString getPrompt() {
		return new AttributedString("kukulkan >", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
	}
}
