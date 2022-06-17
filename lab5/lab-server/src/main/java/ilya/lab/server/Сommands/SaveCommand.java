package ilya.lab.server.Сommands;

import ilya.lab.common.Classes.Route;
import ilya.lab.server.ServerUtil.CollectionManager;
import ilya.lab.server.ServerUtil.XmlParser;

import java.io.IOException;

/**
 * save command
 */
public class SaveCommand extends Command {
    private final CollectionManager manager;
    private final String path;
    public SaveCommand(CollectionManager manager, String path) {
        this.manager = manager;
        this.path = path;
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
        XmlParser.convertCollectionToXml(manager, path);
        getIOManager().printConfirmation("Collection was saved successfully");
    }
}
