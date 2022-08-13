package ilya.server.Commands;

import java.util.Map;

import ilya.common.Classes.Route;
import ilya.common.Requests.ServerResponse;

/**
 * execute_script command
 */
public class ExecuteScriptCommand extends Command {
    private final Map<String, Command> commands;

    public ExecuteScriptCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * executes command with arguments
     *
     * @param args  arguments
     * @param route potential new element
     */
    @Override
    public ServerResponse execute(String[] args, Route route, boolean isFile) {
        //I decided not to delete this class, because, considering how dumb I am,
        //I actually might use it in the future
        return null;
    }
}
