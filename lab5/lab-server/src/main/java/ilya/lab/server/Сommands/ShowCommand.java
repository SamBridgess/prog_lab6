package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Requests.ServerResponse;
import ilya.lab.server.ServerUtil.CollectionManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * show command
 */
public class ShowCommand extends Command {
    private final CollectionManager manager;
    public ShowCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @param route     potential new element
     */
    @Override
    public ServerResponse execute(String[] args, Route route) {
        ArrayList<Route> listCopy = new ArrayList<>(manager.getCollection());
        Collections.sort(listCopy);

        String s = "";
        for (Route r : listCopy) {
            s = s + r + '\n';
        }
        return new ServerResponse(s);
    }
}
