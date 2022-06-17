package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.ServerUtil.CollectionManager;

/**
 * remove_by_id command
 */
public class RemoveByIdCommand extends Command {
    private final CollectionManager manager;
    public RemoveByIdCommand(CollectionManager manager) {
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
        try {
            if (manager.removeRouteByID(Long.parseLong(args[0]))) {
                getIOManager().printConfirmation("Element removed successfully");
            } else {
                getIOManager().printWarning("There is no element with such ID in collection");
                if (getIOManager().getIsFile()) {
                    throw new WrongFileFormatException();
                }
            }
        } catch (NumberFormatException e) {
            getIOManager().printWarning("Invalid command's arguments!");
            if (getIOManager().getIsFile()) {
                throw new WrongFileFormatException();
            }
        }
    }
}
