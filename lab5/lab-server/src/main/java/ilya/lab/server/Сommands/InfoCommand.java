package ilya.lab.server.Сommands;


import ilya.lab.server.IO.IOManager;
import ilya.lab.server.Utility.CollectionManager;

/**
 * info command
 */
public class InfoCommand extends Command {
    private final CollectionManager manager;
    public InfoCommand(IOManager io, CollectionManager manager) {
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
        getIOManager().println(manager.getInfo());
    }
}
