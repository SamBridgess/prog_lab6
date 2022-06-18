package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Requests.ServerResponse;
import ilya.lab.server.ServerUtil.CollectionManager;

/**
 * sort command
 */
public class SortCommand extends Command {
    private final CollectionManager manager;
    public SortCommand(CollectionManager manager) {
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
        manager.sortCollection();

        return new ServerResponse("Collection sorted successfully", false, false);
    }
}
