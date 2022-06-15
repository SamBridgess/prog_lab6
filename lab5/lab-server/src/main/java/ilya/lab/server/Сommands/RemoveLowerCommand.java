package ilya.lab.server.Сommands;

import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.IO.IOManager;
import ilya.lab.server.IO.RouteCreator;
import ilya.lab.server.Utility.CollectionManager;


/**
 * remove_lower command
 */
public class RemoveLowerCommand extends Command {
    private final CollectionManager manager;
    public RemoveLowerCommand(IOManager io, CollectionManager manager) {
        super(0, io);
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
        manager.removeAllLower(new RouteCreator(getIOManager(), manager).createRoute());
        getIOManager().printConfirmation("Elements removed successfully");
    }
}
