package client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Arrays;

public class ClientCommunicationLine {
    private BufferedReader clientConsole;
    private String name;
    private Socket peer;
    private BufferedReader peerInput;
    private OutputStream peerOutput;

    ClientCommunicationLine(Socket peer, BufferedReader peerInput, OutputStream peerOutput) {
        clientConsole = new BufferedReader(new InputStreamReader(System.in));
        this.peer = peer;
        this.peerInput = peerInput;
        this.peerOutput = peerOutput;
    }


    void getName() {
        try {
            System.out.println("Enter your name?");
            name = clientConsole.readLine();
            System.out.println("Line is established...");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void consoleChat() {
        try {
            // Client console..
            getName();
            String line;
            while ((line = clientConsole.readLine()) != null) {
                if (line.equals("STOP")) break;
                // write to server..
                new PrintWriter(peerOutput).println(String.format("%s: %s", name, line));
                peerOutput.flush();
                // read from server..
                System.out.printf("%s: %s\n", peer.getInetAddress(), peerInput.readLine());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void sendFile() {
        try {
            File file = new File("./files/in/img.png");
            peerOutput.write(Files.readAllBytes(file.toPath()));
            peerOutput.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
