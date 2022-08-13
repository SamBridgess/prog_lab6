package ilya.server.Commands;

import ilya.common.Classes.Route;
import ilya.common.Requests.ServerResponse;
import ilya.server.ServerUtil.CollectionManager;
import ilya.server.ServerUtil.XmlParser;

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
    public ServerResponse execute(String[] args, Route route, boolean isFile) throws IOException {
        XmlParser.convertCollectionToXml(manager, path);
        return new ServerResponse("Collection was saved successfully", false);
    }
}
