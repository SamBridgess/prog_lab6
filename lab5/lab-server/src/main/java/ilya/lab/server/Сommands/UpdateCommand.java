package ilya.lab.server.Сommands;


import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.common.Requests.ServerResponse;
import ilya.lab.server.ServerUtil.CollectionManager;


/**
 * update command
 */
public class UpdateCommand extends Command {
    private final CollectionManager manager;
    public UpdateCommand(CollectionManager manager) {
        this.manager = manager;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @param route     potential new element
     * @throws WrongFileFormatException
     * @throws CtrlDException
     */
    @Override
    public ServerResponse execute(String[] args, Route route, boolean isFile) throws WrongFileFormatException, CtrlDException {
        if (manager.isElementIdPresent(Long.valueOf(args[0]))) {
            manager.updateRouteByID(Long.valueOf(args[0]), route);
            return new ServerResponse("Updated element successfully", false, false);
        } else {
            return new ServerResponse("There is no object with such ID in the collection!", false, isFile);
        }


    }
}
