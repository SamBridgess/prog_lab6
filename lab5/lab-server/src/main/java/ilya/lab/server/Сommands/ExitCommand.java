package ilya.lab.server.Сommands;


import ilya.lab.server.IO.IOManager;

import java.io.IOException;

/**
 * exit command
 */
public class ExitCommand extends Command {
    public ExitCommand(IOManager io) {
        super(0, io);
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @throws IOException
     */
    @Override
    public void execute(String[] args) throws IOException {
        getIOManager().close();
        getIOManager().printConfirmation("Exiting...");

        getIOManager().setContinueExecutionFlag(false);
    }
}

