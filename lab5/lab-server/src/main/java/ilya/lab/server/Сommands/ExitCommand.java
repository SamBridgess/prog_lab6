package ilya.lab.server.Сommands;


import ilya.lab.common.Classes.Route;


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
    public void execute(String[] args, Route route) throws IOException {
        getIOManager().close();
        getIOManager().printConfirmation("Exiting...");

        getIOManager().setContinueExecutionFlag(false);
    }
}

