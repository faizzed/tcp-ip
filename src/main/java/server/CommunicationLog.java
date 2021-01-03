package server;

import java.io.PrintWriter;
import java.util.UUID;

public class CommunicationLog {

    public PrintWriter logFile;

    public CommunicationLog() {
        try {
            logFile = new PrintWriter(String.format("./logs/%s.log", UUID.randomUUID()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        logFile.println(message);
    }
}
