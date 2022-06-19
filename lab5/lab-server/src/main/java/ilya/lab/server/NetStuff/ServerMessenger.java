package ilya.lab.server.NetStuff;

import ilya.lab.common.Requests.ClientMessage;
import ilya.lab.common.Requests.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMessenger {
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    public ServerMessenger(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public ClientMessage receive() throws IOException, ClassNotFoundException {
        socket = serverSocket.accept();

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

        System.out.println("about to receive!");
        ClientMessage clientMessage = (ClientMessage) inputStream.readObject();

        System.out.println("received!");
       //serverSocket.close();todo
        return clientMessage;
    }
    public void sendResponse(ServerResponse serverResponse) throws IOException {
        outputStream.writeObject(serverResponse);
    }
}
