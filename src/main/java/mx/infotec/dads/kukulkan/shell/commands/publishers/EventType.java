package mx.infotec.dads.kukulkan.shell.commands.publishers;

import org.jline.utils.AttributedStyle;

/**
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public enum EventType {

    FILE_NAVIGATION(AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));

    private AttributedStyle style;

    private EventType(AttributedStyle style) {

    }

    public AttributedStyle style() {
        return this.style;
    }

}
