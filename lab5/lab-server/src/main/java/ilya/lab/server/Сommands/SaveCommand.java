package ilya.lab.server.Сommands;

import ilya.lab.server.IO.IOManager;
import ilya.lab.server.Utility.CollectionManager;
import ilya.lab.server.Utility.XmlParser;

import java.io.IOException;

/**
 * save command
 */
public class SaveCommand extends Command {
    private final CollectionManager manager;
    private final String path;
    public SaveCommand(IOManager io, CollectionManager manager, String path) {
        super(0, io);
        this.manager = manager;
        this.path = path;
    }

    /**
     * executes command with arguments
     *
     * @param args      arguments
     * @throws IOException
     */
    @Override
    public void execute(String[] args) throws IOException {
        XmlParser.convertCollectionToXml(manager, path);
        getIOManager().printConfirmation("Collection was saved successfully");
    }
}
