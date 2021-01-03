package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class PeerHandler extends Thread {
    private Socket peer;
    private PrintWriter output;
    private InputStream input;

    public PeerHandler(Socket socket) {
        this.peer = socket;
    }

    public void run() {
        try {
            input = peer.getInputStream();
            output = new PrintWriter(peer.getOutputStream());
            BufferedReader serverConsole = new BufferedReader(new InputStreamReader(System.in));

//            new CommunicationLine(input, output, serverConsole).respondFromConsole();
            new CommunicationLine(input, output, serverConsole).handleFileStream();

            System.out.println("Destroying client..");
//            serverConsole.close();
            output.close();
            input.close();
            peer.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}