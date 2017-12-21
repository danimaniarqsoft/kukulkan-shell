package mx.infotec.dads.kukulkan.shell.util;

import org.jline.utils.AttributedStyle;

public enum ExceptionType {
    ERROR("ERROR: ", AttributedStyle.BOLD.foreground(AttributedStyle.RED)), 
    INFO("INFO: ",AttributedStyle.BOLD.foreground(AttributedStyle.BLUE)), 
    WARNING("WARNING: ", AttributedStyle.BOLD.foreground(AttributedStyle.YELLOW));
    
    private String title;
    private AttributedStyle style;

    private ExceptionType(String title, AttributedStyle style) {
        this.title = title;
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public AttributedStyle getStyle() {
        return style;
    }
}
