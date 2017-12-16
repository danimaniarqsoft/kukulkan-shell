package mx.infotec.dads.kukulkan.shell.domain;

/**
 * Native Command show the main commands
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class NativeCommand {

    private String errorMessage;
    private String infoMessage;
    private boolean isActive;
    private NativeCommandType nativeCommandType;

    public NativeCommand(NativeCommandType nct) {
        this.nativeCommandType= nct;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public NativeCommandType getNativeCommandType() {
        return nativeCommandType;
    }

    public void setNativeCommandType(NativeCommandType nativeCommandType) {
        this.nativeCommandType = nativeCommandType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nativeCommandType == null) ? 0 : nativeCommandType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NativeCommand other = (NativeCommand) obj;
        if (nativeCommandType != other.nativeCommandType)
            return false;
        return true;
    }
}
