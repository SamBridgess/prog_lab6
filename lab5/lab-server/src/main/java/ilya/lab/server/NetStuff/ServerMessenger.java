package ilya.lab.server.NetStuff;

import ilya.lab.common.Requests.ClientMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class ServerMessenger {
    public ClientMessage accept() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(3191);
        Socket socket = serverSocket.accept();

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        ClientMessage clientMessage = (ClientMessage) inStream.readObject();
        return clientMessage;
    }

}
