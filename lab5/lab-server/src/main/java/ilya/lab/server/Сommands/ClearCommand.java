package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Requests.ServerResponse;
import ilya.lab.server.ServerUtil.CollectionManager;

/**
 * clear command
 */
public class ClearCommand extends Command {
    private final CollectionManager manager;
    public ClearCommand(CollectionManager manager) {
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
        manager.clearCollection();

        return new ServerResponse("Collection cleared successfully",  false);
    }
}
