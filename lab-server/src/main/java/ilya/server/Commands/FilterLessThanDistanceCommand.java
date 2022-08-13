package ilya.server.Commands;

import ilya.common.Classes.Route;
import ilya.common.Exceptions.WrongFileFormatException;
import ilya.common.Requests.ServerResponse;
import ilya.server.ServerUtil.CollectionManager;

/**
 * filter_less_than_distance command
 */
public class FilterLessThanDistanceCommand extends Command {
    private final CollectionManager manager;
    public FilterLessThanDistanceCommand(CollectionManager manager) {
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
    public ServerResponse execute(String[] args, Route route, boolean isFile) throws WrongFileFormatException {
        String message = "";
        for (Route r : manager.getCollection()) {
            if (r.getDistance() < Float.parseFloat(args[0])) { //todo stream api
                message = message + r + '\n';
            }
        }
        return new ServerResponse(message,  false);
    }
}
