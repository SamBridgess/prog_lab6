package ilya.lab.server;

import ilya.lab.common.Classes.Route;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;

import ilya.lab.common.Requests.ClientMessage;
import ilya.lab.common.Requests.ServerResponse;
import ilya.lab.server.NetStuff.ServerMessenger;
import ilya.lab.server.ServerUtil.CollectionManager;
import ilya.lab.server.ServerUtil.XmlParser;
import ilya.lab.server.Сommands.*;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.ServerSocket;
import java.util.HashMap;

public final class Server {
    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, CtrlDException, WrongFileFormatException, JAXBException {
        int port = 3191;
        String path = "SomeFile.txt";

        CollectionManager manager =  XmlParser.convertXmlToCollection(path);
        HashMap<String, Command> commands = createCommandsMap(manager, path);


        ServerSocket serverSocket = new ServerSocket(port);
        ServerMessenger serverMessenger = new ServerMessenger(serverSocket);
        while(true){
            ClientMessage clientMessage = serverMessenger.recieve();
            String command = clientMessage.getCommand();
            String[] arguments = clientMessage.getArgs();
            Route route = clientMessage.getRoute();
            boolean isFile = clientMessage.getIsFile();

            ServerResponse serverResponse = commands.get(command).execute(arguments, route, isFile);
            serverMessenger.sendResponse(serverResponse);


            XmlParser.convertCollectionToXml(manager, path);
        }
    }
    private static HashMap<String, Command> createCommandsMap(CollectionManager manager, String path) {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(manager));
        commands.put("show", new ShowCommand(manager));
        commands.put("add", new AddCommand(manager));
        commands.put("update", new UpdateCommand(manager));
        commands.put("remove_by_id", new RemoveByIdCommand(manager));
        commands.put("clear", new ClearCommand(manager));
        commands.put("save", new SaveCommand(manager, path));
        commands.put("execute_script", new ExecuteScriptCommand(commands));
        commands.put("exit", new ExitCommand());
        commands.put("remove_first", new RemoveFirstCommand(manager));
        commands.put("remove_lower", new RemoveLowerCommand(manager));
        commands.put("sort", new SortCommand(manager));
        commands.put("filter_less_than_distance", new FilterLessThanDistanceCommand(manager));
        commands.put("print_ascending", new PrintAscendingCommand(manager));
        commands.put("print_field_descending_distance", new PrintFieldDescendingDistanceCommand(manager));
        return commands;
    }
}
