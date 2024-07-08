package mundo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Transmisor {
    public void socket(String msg, String ip) {
        try {
            Socket client = new Socket(ip, 5000);
            DataOutputStream outBuffer = new DataOutputStream(client.getOutputStream());
            outBuffer.writeUTF(msg);
            client.close();
        } catch (UnknownHostException e) {
            System.err.println("Client: socket(1) : UnknownHostException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Client: socket(2) : IOException: " + e.getMessage());
        }
    }
}
