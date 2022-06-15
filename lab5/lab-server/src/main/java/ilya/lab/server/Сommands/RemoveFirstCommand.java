package ilya.lab.server.Сommands;

import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.IO.IOManager;
import ilya.lab.server.Utility.CollectionManager;

/**
 * remove_first command
 */
public class RemoveFirstCommand extends Command {
    private final CollectionManager manager;
    public RemoveFirstCommand(IOManager io, CollectionManager manager) {
        super(0, io);
        this.manager = manager;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @throws WrongFileFormatException
     */
    @Override
    public void execute(String[] args) throws WrongFileFormatException {
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
