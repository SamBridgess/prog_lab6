package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Requests.ServerResponse;
import ilya.lab.server.ServerUtil.CollectionManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * print_ascending command
 */
public class PrintAscendingCommand extends Command {
    private final CollectionManager manager;
    public PrintAscendingCommand(CollectionManager manager) {

        this.manager = manager;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @param route     potential new element
     */
    @Override
    public ServerResponse execute(String[] args, Route route, boolean isFile) {
        ArrayList<Route> listCopy = new ArrayList<>(manager.getCollection());
        Collections.sort(listCopy);

        String message = "";
        for (Route r : listCopy) {
            message = message + r + '\n';
        }
        return new ServerResponse(message, false, false);
    }
}
