package ilya.lab.client;


import ilya.lab.client.ClientUtil.CommandSplitter;
import ilya.lab.client.IO.IOManager;
import ilya.lab.common.Requests.ClientMessage;
import ilya.lab.client.Messages.ClientMessenger;
import ilya.lab.common.Exceptions.CtrlDException;
import ilya.lab.common.Exceptions.WrongFileFormatException;

import java.io.*;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try (IOManager io = new IOManager(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true))) {
            while (io.getContinueExecutionFlag()) {
                try {
                    io.print(">>> ");
                    String s = io.getNextLine();
                    CommandSplitter commandSplitter = new CommandSplitter(s);
                    ClientMessage commandMessage = new ClientMessage(commandSplitter.getCommand(), commandSplitter.getArgs(), io);


                    ClientMessenger.send(commandMessage);
                    //LineExecuter.executeLine(s, commands, io); todo must be on server
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
}
