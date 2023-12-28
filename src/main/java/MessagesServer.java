import Server.Server;

import java.net.ServerSocket;

public class MessagesServer {
    public static void main(String[] args) {
        Server server = new Server();
        ServerSocket serverSocket = server.createServer();
        server.connectClient(serverSocket);
        server.serverClosing(serverSocket);
    }
}