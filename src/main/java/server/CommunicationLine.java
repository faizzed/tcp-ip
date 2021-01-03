package server;

import java.io.*;

public class CommunicationLine {

    private final InputStream peerInput;
    private final PrintWriter peerOutput;
    private final BufferedReader serverConsole;

    CommunicationLine(InputStream peerInput, PrintWriter peerOutput, BufferedReader serverConsole) {
        this.peerInput = peerInput;
        this.peerOutput = peerOutput;
        this.serverConsole = serverConsole;
    }

    void respondFromConsole() {
        try {
            // Server's own console..
            String line;
            while ((line = new BufferedReader(new InputStreamReader(peerInput)).readLine()) != null) {
                if (line.equals("STOP")) break;
                System.out.printf("%s\n", line);
                var serverReply = serverConsole.readLine();
                peerOutput.println(serverReply);
                peerOutput.flush();
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    void handleFileStream() {
        String filePath = "./files/out/peer-file-received.png";

        try(var fileOutStream = new FileOutputStream(filePath)) {
            fileOutStream.write(peerInput.readAllBytes());
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
