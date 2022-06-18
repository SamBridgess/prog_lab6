package ilya.lab.server.Сommands;


import ilya.lab.common.Classes.Route;
import ilya.lab.common.Requests.ServerResponse;


import java.io.IOException;

/**
 * exit command
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @param route     potential new element
     * @throws IOException
     */
    @Override
    public ServerResponse execute(String[] args, Route route, boolean isFile) throws IOException {
        return new ServerResponse("Exiting...", true, false);
    }
}

