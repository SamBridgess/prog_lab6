package ilya.lab.common.Requests;

import ilya.lab.common.Classes.Route;
import java.io.Serializable;

public class ClientMessage implements Serializable {
    private String command;
    private String[] args;
    private Route route;

    public ClientMessage(String command, String[] args, Route route) {
        this.command = command;
        this.args = args;
        this.route = route;
    }
    public String getCommand() {
        return command;
    }
    public String[] getArgs() {
        return args;
    }
    public Route getRoute() {
        return route;
    }

}
