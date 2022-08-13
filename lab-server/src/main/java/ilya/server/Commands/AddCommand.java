package ilya.server.Commands;


import ilya.common.Classes.Route;
import ilya.common.Exceptions.CtrlDException;
import ilya.common.Exceptions.WrongFileFormatException;
import ilya.common.Requests.ServerResponse;
import ilya.server.ServerUtil.CollectionManager;


/**
 * add command
 */
public class AddCommand extends Command {
    private final CollectionManager manager;
    public AddCommand(CollectionManager manager) {
        this.manager = manager;
    }
    /**
     * executes command with arguments
     * @param args      arguments
     * @param route     potential new element
     * @throws WrongFileFormatException
     * @throws CtrlDException
     */
    @Override
    public ServerResponse execute(String[] args, Route route, boolean isFile) throws WrongFileFormatException, CtrlDException {
        route.setId(manager.assignNewId());
        manager.addNewElement(route);

        return new ServerResponse("Element added successfully",  false);
    }
}
