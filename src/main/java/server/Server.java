package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    private ServerSocket server;
    private BufferedReader input;
    private PrintWriter output;
    private Socket client;
    private BufferedReader serverConsole;

    public static void main(String[] args) {
        new Server().startServer();
    }

    void startServer() {
        setup().serve().destroy();
    }

    Server setup() {
        try {
            server = new ServerSocket(6666);
            client = server.accept();
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintWriter(client.getOutputStream());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return this;
    }

    Server serve() {
        try {

            // Server's own console..
            serverConsole = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = input.readLine()) != null) {
                if (line.equals("STOP")) break;
                System.out.printf("%s: %s\n",client.getInetAddress(), line);
                output.println(serverConsole.readLine());
                output.flush();
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return this;
    }

    void destroy() {
        try {
            output.close();
            input.close();
            serverConsole.close();
            client.close();
            server.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
