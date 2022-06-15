package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.IO.IOManager;
import ilya.lab.server.Utility.CollectionManager;

/**
 * filter_less_than_distance command
 */
public class FilterLessThanDistanceCommand extends Command {
    private final CollectionManager manager;
    public FilterLessThanDistanceCommand(IOManager io, CollectionManager manager) {
        super(1, io);
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
        try {
            for (Route r : manager.getCollection()) {
                if (r.getDistance() < Float.parseFloat(args[0])) {
                    getIOManager().println(r);
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
