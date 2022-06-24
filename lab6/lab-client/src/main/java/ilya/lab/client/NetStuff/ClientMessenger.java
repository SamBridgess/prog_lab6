package ilya.lab.client.NetStuff;

import ilya.lab.common.Requests.ClientMessage;
import ilya.lab.common.Requests.ServerResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMessenger {
    private String host = "127.0.0.1";
    private int port = 3191;

    public ServerResponse send(ClientMessage clientMessage) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(host, port);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream outStream = new ObjectOutputStream(bytes);

        outStream.writeObject(clientMessage);
        socket.getOutputStream().write(bytes.toByteArray());

        outStream.close();// todo
        byte[] b = new byte[65500];
        socket.getInputStream().read(b);
        ObjectInputStream inStream = new ObjectInputStream(new ByteArrayInputStream(b));
        socket.close();// todo
        return (ServerResponse) inStream.readObject();
    }
}