package ilya.lab.server.Сommands;

import ilya.lab.client.IO.IOManager;
import ilya.lab.client.Utility.CollectionManager;

/**
 * clear command
 */
public class ClearCommand extends Command {
    private final CollectionManager manager;
    public ClearCommand(IOManager io, CollectionManager manager) {
        super(0, io);
        this.manager = manager;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     */
    @Override
    public void execute(String[] args) {
        manager.clearCollection();
        getIOManager().printConfirmation("Collection cleared successfully");

    }
}
