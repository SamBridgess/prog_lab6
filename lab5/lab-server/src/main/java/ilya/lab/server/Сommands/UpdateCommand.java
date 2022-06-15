package ilya.lab.server.Сommands;


import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.IO.IOManager;
import ilya.lab.server.IO.RouteCreator;
import ilya.lab.server.Utility.CollectionManager;


/**
 * update command
 */
public class UpdateCommand extends Command {
    private final CollectionManager manager;
    public UpdateCommand(IOManager io, CollectionManager manager) {
        super(1, io);
        this.manager = manager;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @throws WrongFileFormatException
     * @throws CtrlDException
     */
    @Override
    public void execute(String[] args) throws WrongFileFormatException, CtrlDException {
        try {
            if (manager.isElementIdPresent(Long.valueOf(args[0]))) {
                Route route = new RouteCreator(getIOManager(), manager).createRoute();
                manager.updateRouteByID(Long.valueOf(args[0]), route);
                getIOManager().printConfirmation("Updated element successfully");
            } else {
                getIOManager().printWarning("There is no object with such ID in the collection!");
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
