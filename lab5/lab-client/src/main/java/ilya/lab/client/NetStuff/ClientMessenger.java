package ilya.lab.client.NetStuff;

import ilya.lab.common.Requests.ClientMessage;
import ilya.lab.common.Requests.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMessenger {
    private String host = "127.0.0.1";
    private int port = 3191;
    public ServerResponse send(ClientMessage clientMessage) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(host, port);

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        outStream.writeObject(clientMessage);

        ServerResponse serverResponse = (ServerResponse) inStream.readObject();

        socket.close();//todo
        outStream.close();//todo
        return serverResponse;
    }
}
