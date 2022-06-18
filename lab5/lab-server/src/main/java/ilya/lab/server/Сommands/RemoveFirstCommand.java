package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.common.Requests.ServerResponse;
import ilya.lab.server.ServerUtil.CollectionManager;

/**
 * remove_first command
 */
public class RemoveFirstCommand extends Command {
    private final CollectionManager manager;
    public RemoveFirstCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @param route     potential new element
     * @throws WrongFileFormatException
     */
    @Override
    public ServerResponse execute(String[] args, Route route, boolean isFile) throws WrongFileFormatException {
        if (manager.removeRouteByIdx(0)) {
            return new ServerResponse("First element removed succesfully", false, false);
        } else {
            return new ServerResponse("Collection is empty, no first element", false, isFile);
        }
    }
}
