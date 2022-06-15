package ilya.lab.server.Сommands;


import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.IO.IOManager;
import ilya.lab.server.IO.RouteCreator;
import ilya.lab.server.Utility.CollectionManager;


/**
 * add command
 */
public class AddCommand extends Command {
    private final CollectionManager manager;
    public AddCommand(IOManager io, CollectionManager manager) {
        super(0, io);
        this.manager = manager;
    }

    /**
     * executes command with arguments
     * @param args      arguments
     * @throws WrongFileFormatException
     * @throws CtrlDException
     */
    @Override
    public void execute(String[] args) throws WrongFileFormatException, CtrlDException {
        Route route = new RouteCreator(getIOManager(), manager).createRoute();
        manager.addNewElement(route);
        getIOManager().printConfirmation("Element added successfully");
    }
}
