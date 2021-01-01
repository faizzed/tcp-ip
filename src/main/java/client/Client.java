package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private PrintWriter output;
    private BufferedReader clientConsole;

    public static void main(String[] args) throws IOException {
        new Client().startClient(args);
    }

    void startClient(String ...args) {
        setup("127.0.0.1", 6666).send().destroy();
    }

    Client setup(String host, int port) {
        try {
            connection = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            output = new PrintWriter(connection.getOutputStream());
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return this;
    }

    /**
    * Read a line from console and send to server..
    * */
    Client send() {
        try {
            // Client console..
            clientConsole = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = clientConsole.readLine()) != null) {
                if (line.equals("STOP")) break;
                // write to server..
                output.println(line);
                output.flush();
                // read from server..
                System.out.printf("%s: %s\n", connection.getInetAddress(), input.readLine());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return this;
    }

    void destroy() {
        try {
            input.close();
            output.close();
            clientConsole.close();
            connection.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
