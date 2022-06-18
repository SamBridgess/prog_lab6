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
    public ServerMessenger(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    //private int port = 3191;
    public ClientMessage recieve() throws IOException, ClassNotFoundException {
        socket = serverSocket.accept();

        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        ClientMessage clientMessage = (ClientMessage) inStream.readObject();

       //serverSocket.close();todo
        return clientMessage;
    }
    public void sendResponse(ServerResponse serverResponse) throws IOException {
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.writeObject(serverResponse);
    }

}
