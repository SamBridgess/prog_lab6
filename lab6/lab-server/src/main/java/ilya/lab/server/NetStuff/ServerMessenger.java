package ilya.lab.server.NetStuff;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import ilya.lab.common.Requests.ClientMessage;
import ilya.lab.common.Requests.ServerResponse;

public class ServerMessenger {
    private ServerSocketChannel serverSocket;
    private SocketChannel socket;

    public ServerMessenger(ServerSocketChannel serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ClientMessage receive() throws IOException, ClassNotFoundException {
        socket = serverSocket.accept();

        ByteBuffer buf = ByteBuffer.allocate(65536);
        socket.read(buf);

        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(buf.array()));

        ClientMessage clientMessage = (ClientMessage) inputStream.readObject();
        System.out.println("Packet received!");

        return clientMessage;
    }

    public void sendResponse(ServerResponse serverResponse) throws IOException {

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(b);

        oos.writeObject(serverResponse);

        socket.write(ByteBuffer.wrap(b.toByteArray()));
        System.out.println("Packet sent!");
    }
}