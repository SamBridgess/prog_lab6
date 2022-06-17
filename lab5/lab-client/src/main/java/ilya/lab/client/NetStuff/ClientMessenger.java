package ilya.lab.client.NetStuff;

import ilya.lab.common.Requests.ClientMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMessenger {
    public void send(ClientMessage clientMessage) throws IOException {
        Socket sock = new Socket("127.0.0.1", 6789);
        OutputStream os = sock.getOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(clientMessage);
    }
}
