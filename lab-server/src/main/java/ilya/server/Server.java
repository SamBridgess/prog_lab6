package ilya.server;



import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;

import java.net.InetSocketAddress;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.JAXBException;

import ilya.common.Classes.Route;
import ilya.common.Exceptions.CtrlDException;
import ilya.common.Exceptions.WrongFileFormatException;
import ilya.common.Requests.ClientMessage;
import ilya.common.Requests.ServerResponse;
import ilya.common.util.AddresValidator;
import ilya.server.ServerUtil.CollectionManager;
import ilya.server.ServerUtil.XmlParser;
import ilya.server.Commands.AddCommand;
import ilya.server.Commands.ClearCommand;
import ilya.server.Commands.Command;
import ilya.server.Commands.ExecuteScriptCommand;
import ilya.server.Commands.ExitCommand;
import ilya.server.Commands.FilterLessThanDistanceCommand;
import ilya.server.Commands.HelpCommand;
import ilya.server.Commands.InfoCommand;
import ilya.server.Commands.PrintAscendingCommand;
import ilya.server.Commands.PrintFieldDescendingDistanceCommand;
import ilya.server.Commands.RemoveByIdCommand;
import ilya.server.Commands.RemoveFirstCommand;
import ilya.server.Commands.RemoveLowerCommand;
import ilya.server.Commands.SaveCommand;
import ilya.server.Commands.ShowCommand;
import ilya.server.Commands.SortCommand;
import ilya.server.Commands.UpdateCommand;

public final class Server {
    private static Selector selector;
    private static InetSocketAddress inetSocketAddress;
    private static Set<SocketChannel> session;
    private Server() {
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, CtrlDException, WrongFileFormatException, JAXBException {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            //args = new String[1];
            //args[0] = "5555";
            if (!AddresValidator.checkPort(args)) {
                System.out.println("Please enter Port correctly!");
                return;
            }

            int port = Integer.parseInt(args[0]);
            System.out.println("Server is working on port " + port);

            String collectionPath = "Collection.xml";
            CollectionManager manager = XmlParser.convertXmlToCollection(collectionPath);
            HashMap<String, Command> commands = createCommandsMap(manager, collectionPath);


            inetSocketAddress = new InetSocketAddress(port);
            selector = Selector.open();
            session = new HashSet<>();

            serverSocketChannel.bind(inetSocketAddress);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        ClientMessage clientMessage = receive(key);
                        if (clientMessage == null) {
                            continue;
                        }

                        String command = clientMessage.getCommand();
                        String[] arguments = clientMessage.getArgs();
                        Route route = clientMessage.getRoute();
                        boolean isFile = clientMessage.getIsFile();

                        ServerResponse serverResponse = commands.get(command).execute(arguments, route, isFile);

                        sendResponse(key, serverResponse);
                    }
                }
            }
        }
    }
    private static void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverSocketChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        session.add(channel);
        System.out.println("User accepted: " + channel.socket().getRemoteSocketAddress());
    }
    private static ClientMessage receive(SelectionKey key) throws IOException, ClassNotFoundException {
        final int bufferSize = 65536;

        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);

        int numRead = channel.read(byteBuffer);
        if (numRead == -1) {
            session.remove(channel);
            System.out.println("Exchanged finished: " + channel.socket().getRemoteSocketAddress() + "\n");
            channel.close();
            key.cancel();
            return null;
        }
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteBuffer.array()));

        ClientMessage clientMessage = (ClientMessage) inputStream.readObject();

        byteBuffer.clear();
        System.out.println("Packet received!");
        return clientMessage;
    }
    private static void sendResponse(SelectionKey key, ServerResponse serverResponse) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(b);

        objectOutputStream.writeObject(serverResponse);

        channel.write(ByteBuffer.wrap(b.toByteArray()));
        System.out.println("Packet sent!");
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
