package ilya.lab.common.Requests;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    private String responseMessage;
    private boolean disconnectClient;
    private boolean wrongScriptFormat;
    public ServerResponse(String responseMessage, boolean disconnectClient, boolean wrongScriptFormat) {
        this.responseMessage = responseMessage;
        this.disconnectClient = disconnectClient;
        this.wrongScriptFormat = wrongScriptFormat;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
    public boolean getDisconnectClient() {
        return disconnectClient;
    }
    public boolean getWrongScriptFormat() {
        return wrongScriptFormat;
    }
}
