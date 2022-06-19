package ilya.lab.client;


import ilya.lab.client.ClientUtil.*;
import ilya.lab.client.IO.IOManager;
import ilya.lab.common.Classes.Route;
import ilya.lab.common.Requests.ClientMessage;
import ilya.lab.client.NetStuff.ClientMessenger;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;
import ilya.lab.common.Requests.ServerResponse;

import java.io.*;
import java.util.HashMap;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try (IOManager io = new IOManager(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true))) {
            HashMap<String, CommandRules> commandsInfo = createCommandsInfo();
            ClientMessenger clientMessenger = new ClientMessenger();
            ScriptManager scriptManager = new ScriptManager(io);
            while (io.getContinueExecutionFlag()) {
                try {
                    if(!io.getIsFile()) {
                        io.print(">>> ");
                    }
                    String s = io.getNextLine();
                    CommandSplitter commandSplitter = new CommandSplitter(s);
                    String command = commandSplitter.getCommand();
                    String[] arguments = commandSplitter.getArgs();

                    if(LineValidator.checkLine(command, arguments, commandsInfo, io)) {
                        if(command.equals("execute_script")) {
                            scriptManager.addScript(arguments[0]);
                        } else {
                            Route route = null;
                            if (commandsInfo.get(command).getRequiresNewRoute()) {
                                route = new RouteCreator(io).createRoute();
                            }

                            ClientMessage clientMessage = new ClientMessage(commandSplitter.getCommand(), commandSplitter.getArgs(), route, io.getIsFile());
                            ServerResponse serverResponse = clientMessenger.send(clientMessage);
                            io.println(serverResponse.getResponseMessage());

                            if(serverResponse.getDisconnectClient()) {
                                io.setContinueExecutionFlag(false);
                                io.close();
                                return;
                            }
                            if(serverResponse.getWrongScriptFormat()) { //todo wrong file format?
                                throw new WrongFileFormatException();
                            }
                            while(io.isLastFileExecuted()) {
                                io.printConfirmation(io.getFileStack().peek().getName() + " executed successfully");
                                io.popStacks();
                            }
                        }
                    } else {
                        io.printWarning("Invalid arguments");
                        if(io.getIsFile()) {
                            throw new WrongFileFormatException();
                        }
                    }
                } catch (CtrlDException e) {
                    io.clearStacks();
                    io.printWarning("ctrl + D detected! Exiting program...");
                    return;
                } catch (WrongFileFormatException e) {
                    io.clearStacks();
                    io.printWarning("Can't execute script(s) further! Wrong file(s) format");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Unexpected exception!");
        }
    }
    public static HashMap<String, CommandRules> createCommandsInfo() {
        HashMap<String, CommandRules> commandsInfo = new HashMap<>();
        commandsInfo.put("help", new CommandRules(0));
        commandsInfo.put("info", new CommandRules(0));
        commandsInfo.put("show", new CommandRules(0));
        commandsInfo.put("add", new CommandRules(0, true));
        commandsInfo.put("update", new CommandRules(1, true));
        commandsInfo.put("remove_by_id", new CommandRules(1));
        commandsInfo.put("clear", new CommandRules(0));
        commandsInfo.put("save", new CommandRules(0));
        commandsInfo.put("execute_script", new CommandRules(1));
        commandsInfo.put("exit", new CommandRules(0));
        commandsInfo.put("remove_first", new CommandRules(0));
        commandsInfo.put("remove_lower", new CommandRules(0, true));
        commandsInfo.put("sort", new CommandRules(0));
        commandsInfo.put("filter_less_than_distance", new CommandRules(1));
        commandsInfo.put("print_ascending", new CommandRules(0));
        commandsInfo.put("print_field_descending_distance", new CommandRules(0));
        return commandsInfo;
    }



}
