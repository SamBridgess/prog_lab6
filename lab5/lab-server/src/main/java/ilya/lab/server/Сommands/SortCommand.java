package ilya.lab.server.Сommands;

import ilya.lab.server.IO.IOManager;
import ilya.lab.server.Utility.CollectionManager;

/**
 * sort command
 */
public class SortCommand extends Command {
    private final CollectionManager manager;
    public SortCommand(IOManager io, CollectionManager manager) {
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
        manager.sortCollection();
        getIOManager().printConfirmation("Collection sorted successfully");
    }
}
