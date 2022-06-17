package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.WrongFileFormatException;
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
    public void execute(String[] args, Route route) throws WrongFileFormatException {
        if (manager.removeRouteByIdx(0)) {
            getIOManager().printConfirmation("First element removed successfully");
        } else {
            getIOManager().printWarning("Collection is empty, no first element");
            if (getIOManager().getIsFile()) {
                throw new WrongFileFormatException();
            }
        }
    }
}
