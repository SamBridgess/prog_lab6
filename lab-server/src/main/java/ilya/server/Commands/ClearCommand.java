package ilya.server.Commands;

import ilya.common.Classes.Route;
import ilya.common.Requests.ServerResponse;
import ilya.server.ServerUtil.CollectionManager;

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
