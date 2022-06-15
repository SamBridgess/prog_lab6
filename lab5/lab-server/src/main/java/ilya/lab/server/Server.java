package ilya.lab.server;

import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.server.IO.IOManager;
import ilya.lab.server.Utility.CollectionManager;
import ilya.lab.server.Utility.LineExecuter;
import ilya.lab.server.Utility.XmlParser;
import ilya.lab.server.Сommands.*;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.HashMap;

public final class Server {

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try (IOManager io = new IOManager(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true))) {
            String path = "SomeFile.xml";
            /*String path;
            if (args.length != 1) {
                io.printWarning("This program only takes one argument!");
                return;
            } else {
                path = args[0];
            }*/
            CollectionManager manager = new CollectionManager();
            try {
                if (!(new File(path).length() == 0)) {
                    manager = XmlParser.convertXmlToCollection(path);
                }
            } catch (JAXBException | IllegalArgumentException e) {
                io.printWarning("Collection file has wrong format or doesn't exist! Exiting program...");
                return;
            }
            manager.setMinId();

            HashMap<String, Command> commands = createCommandsMap(manager, io, path);
            while (io.getContinueExecutionFlag()) {
                try {
                    io.print(">>> ");
                    String s = io.getNextLine();
                    LineExecuter.executeLine(s, commands, io);
                } catch (WrongFileFormatException e) {
                    io.printWarning("Can't execute script(s) further! Wrong file(s) format");
                } catch (CtrlDException e) {
                    io.printWarning("ctrl + D detected! Exiting program...");
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Unexpected exception!");
        }
    }
    public static HashMap<String, Command> createCommandsMap(CollectionManager manager, IOManager io, String path) {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("help", new HelpCommand(io));
        commands.put("info", new InfoCommand(io, manager));
        commands.put("show", new ShowCommand(io, manager));
        commands.put("add", new AddCommand(io, manager));
        commands.put("update", new UpdateCommand(io, manager));
        commands.put("remove_by_id", new RemoveByIdCommand(io, manager));
        commands.put("clear", new ClearCommand(io, manager));
        commands.put("save", new SaveCommand(io, manager, path));
        commands.put("execute_script", new ExecuteScriptCommand(io, commands));
        commands.put("exit", new ExitCommand(io));
        commands.put("remove_first", new RemoveFirstCommand(io, manager));
        commands.put("remove_lower", new RemoveLowerCommand(io, manager));
        commands.put("sort", new SortCommand(io, manager));
        commands.put("filter_less_than_distance", new FilterLessThanDistanceCommand(io, manager));
        commands.put("print_ascending", new PrintAscendingCommand(io, manager));
        commands.put("print_field_descending_distance", new PrintFieldDescendingDistanceCommand(io, manager));
        return commands;
    }
}
