package mx.infotec.dads.kukulkan.shell.domain;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

public class AboutInfo {

    private String version;

    private String name;

    public AboutInfo(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        AttributedString a = new AttributedString(name + " v" + version,
                AttributedStyle.BOLD.foreground(AttributedStyle.BLUE));
        return a.toAnsi();
    }
}