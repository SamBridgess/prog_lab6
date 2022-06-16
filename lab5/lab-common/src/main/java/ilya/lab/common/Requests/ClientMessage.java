package ilya.lab.common.Requests;

import ilya.lab.common.IO.IOManager;
import ilya.lab.client.IO.RouteCreator;
import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientMessage implements Serializable {
    private String commandName;
    private String[] args;
    private Route route = null;
    private IOManager io;

    private ArrayList<String> commandsWithNewRoute = new ArrayList<>(Arrays.asList("add", "update", "remove_lower"));
 ;
    public ClientMessage(String commandName, String[] args, IOManager io) throws CtrlDException, WrongFileFormatException {
        this.commandName = commandName;
        this.args = args;
        this.io = io;

        if(commandsWithNewRoute.contains(commandName)) {
            //  route = new RouteCreator(io).createRoute();todo
        }
    }

}
