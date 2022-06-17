package ilya.lab.client;


import ilya.lab.client.ClientUtil.CommandInfo;
import ilya.lab.client.ClientUtil.CommandSplitter;
import ilya.lab.client.ClientUtil.LineValidator;
import ilya.lab.client.ClientUtil.RouteCreator;
import ilya.lab.client.IO.IOManager;
import ilya.lab.common.Classes.Route;
import ilya.lab.common.Requests.ClientMessage;
import ilya.lab.client.NetStuff.ClientMessenger;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;

import java.io.*;
import java.util.HashMap;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try (IOManager io = new IOManager(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true))) {
            HashMap<String, CommandInfo> commandsInfo = createCommandsInfo();
            ClientMessenger clientMessenger = new ClientMessenger();
            while (io.getContinueExecutionFlag()) { // todo exit wont work properly here
                try {
                    io.print(">>> ");
                    String s = io.getNextLine();
                    CommandSplitter commandSplitter = new CommandSplitter(s);
                    String command = commandSplitter.getCommand();
                    String[] arguments = commandSplitter.getArgs();

                    if(LineValidator.checkLine(command, arguments, commandsInfo, io)) {
                        Route route = null;
                        if (commandsInfo.get(command).getRequiresNewRoute()) {
                            route = new RouteCreator(io).createRoute();
                        }

                        ClientMessage clientMessage = new ClientMessage(commandSplitter.getCommand(), commandSplitter.getArgs(), route);
                        clientMessenger.send(clientMessage);
                    }
                } catch (CtrlDException e) {
                    io.printWarning("ctrl + D detected! Exiting program...");
                    return;
                } catch (WrongFileFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Unexpected exception!");
        }
    }
    public static HashMap<String, CommandInfo> createCommandsInfo() {
        HashMap<String, CommandInfo> commandsInfo = new HashMap<>();
        commandsInfo.put("help", new CommandInfo(0, false));
        commandsInfo.put("info", new CommandInfo(0, false));
        commandsInfo.put("show", new CommandInfo(0, false));
        commandsInfo.put("add", new CommandInfo(0, true));
        commandsInfo.put("update", new CommandInfo(1, true));
        commandsInfo.put("remove_by_id", new CommandInfo(1, false));
        commandsInfo.put("clear", new CommandInfo(0, false));
        commandsInfo.put("save", new CommandInfo(0, false));
        commandsInfo.put("execute_script", new CommandInfo(1, false));
        commandsInfo.put("exit", new CommandInfo(0, false));
        commandsInfo.put("remove_first", new CommandInfo(0, false));
        commandsInfo.put("remove_lower", new CommandInfo(0, true));
        commandsInfo.put("sort", new CommandInfo(0, false));
        commandsInfo.put("filter_less_than_distance", new CommandInfo(1, false));
        commandsInfo.put("print_ascending", new CommandInfo(0, false));
        commandsInfo.put("print_field_descending_distance", new CommandInfo(0, false));
        return commandsInfo;
    }



}
