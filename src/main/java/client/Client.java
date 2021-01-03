package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * TCP/IP Socket.
 *
 * A socket is an endpoint that establish a line with an IP and port on another machine.
 * Here we are connecting on the same machine.
 * The established line can send byte streams of data. This client reads a line from console and wait for server to reply.
 *
 * Client is application on its own..
 * Start as many clients as you like from IDE or Console..
* */
public class Client {
    private Socket connection;
    private BufferedReader input;
    private OutputStream output;

    public static void main(String[] args) {
        new Client().startClient(args);
    }

    void startClient(String ...args) {
        setup("127.0.0.1", 6666);
//        new ClientCommunicationLine(connection, input, output).consoleChat();
        new ClientCommunicationLine(connection, input, output).sendFile();
        destroy();
    }

    void setup(String host, int port) {
        try {
            connection = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            output = connection.getOutputStream();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    void destroy() {
        try {
            input.close();
            output.close();
            connection.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
