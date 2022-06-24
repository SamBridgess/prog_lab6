package ilya.lab.server.Сommands;


import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.common.Requests.ServerResponse;
import ilya.lab.server.ServerUtil.CollectionManager;


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
