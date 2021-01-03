package server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server implementation on TCP/IP protocol
 *
 * This class setup a server which read a line from client and reply with a line from its console..
 * Send STOP from either side to stop the communication.
 *
 * Server is application on its own..
 * Start the server by running this class from IDE or console.
* */
public class Server {

    public static void main(String[] args) {
        new Server().serve();
    }

    /**
    * Accept is blocking, until it receives a client.
    * */
    void serve() {
        try {
            ServerSocket server = new ServerSocket(6666);
            while (true) {
                System.out.println("Waiting for a client..");
                Socket peer = server.accept();
                new PeerHandler(peer).start();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
