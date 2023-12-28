package Client;

import lombok.Getter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Getter
public class ClientSocketWrapper implements AutoCloseable {
    private final Socket socket;
    private final Scanner input;
    private final PrintWriter output;

    ClientSocketWrapper(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}