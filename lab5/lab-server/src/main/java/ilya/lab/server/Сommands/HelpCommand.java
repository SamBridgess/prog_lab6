package ilya.lab.server.Сommands;

import ilya.lab.server.IO.IOManager;
import ilya.lab.server.Utility.CommandsManager;

/**
 * help command
 */
public class HelpCommand extends Command {
    public HelpCommand(IOManager io) {
        super(0, io);
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     */
    @Override
    public void execute(String[] args) {
        getIOManager().println(CommandsManager.getCommandsHelp());
    }
}
