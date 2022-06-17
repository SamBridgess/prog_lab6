package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.common.Requests.ServerResponse;
import ilya.lab.server.ServerUtil.CollectionManager;


/**
 * remove_lower command
 */
public class RemoveLowerCommand extends Command {
    private final CollectionManager manager;
    public RemoveLowerCommand(CollectionManager manager) {
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
    public ServerResponse execute(String[] args, Route route) throws WrongFileFormatException, CtrlDException {
        manager.removeAllLower(route);

        return new ServerResponse("Elements removed successfully");
    }
}
