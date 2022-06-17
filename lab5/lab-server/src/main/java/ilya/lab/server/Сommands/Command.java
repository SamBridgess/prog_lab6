package ilya.lab.server.Сommands;


import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.common.Requests.ServerResponse;


import java.io.IOException;
/**
 * parent of all commands
 */
public abstract class Command {
    public Command() {
    }
    public abstract ServerResponse execute(String[] args, Route route) throws IOException, WrongFileFormatException, CtrlDException;
}
