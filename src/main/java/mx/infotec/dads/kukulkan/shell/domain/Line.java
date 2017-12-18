package mx.infotec.dads.kukulkan.shell.domain;

import java.io.Serializable;

/**
 * Line represent a line of text in the console. I line have the output text in
 * a terminal.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Line implements Serializable {

    private static final long serialVersionUID = 1L;

    private String text;

    private String description;

    public Line(String lineText) {
        this.text = lineText;
    }

    public Line(String lineText, String description) {
        this.text = lineText;
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public String getDescription() {
        return description;
    }

    public void setText(String lineText) {
        this.text = lineText;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
