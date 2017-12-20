package mx.infotec.dads.kukulkan.shell.domain;

/**
 * Args
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Args {

    private String param;

    public Args(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return param;
    }

}
