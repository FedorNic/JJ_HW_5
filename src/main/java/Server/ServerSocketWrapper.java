package Server;

import lombok.Getter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Getter
public class ServerSocketWrapper implements AutoCloseable {
    private final Socket socket;
    private final Scanner input;
    private final PrintWriter output;
    private String userName;

    ServerSocketWrapper(String userName, Socket socket) throws IOException {
        this.userName = userName;
        this.socket = socket;
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    @Override
    public String toString() {
        return "Клиент [ socket = " + socket.getPort() + "]";
    }
}