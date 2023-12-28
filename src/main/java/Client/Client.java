package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    boolean connection = true;

    public void connectClient() {
        final int PORT = 22;
        final String HOST = "localhost";
        Scanner userMessage = new Scanner(System.in);
        System.out.println("Введите имя");
        String userName = userMessage.nextLine();

        try {
            Socket client = new Socket(HOST, PORT);
            ClientSocketWrapper socketWrapper = new ClientSocketWrapper(client);
            socketWrapper.getOutput().println(userName);

            new Thread(() -> {
                while (connection) {
                    String outMessage = socketWrapper.getInput().nextLine();
                    System.out.println(outMessage);
                    if (Objects.equals("Сервер отключен", outMessage)) {
                        System.exit(0);
                    }
                    if (Objects.equals("Соединение прервано. Обратитесь к администратору", outMessage)) {
                        socketWrapper.getOutput().println("q");
                        System.exit(0);
                    }
                }
            }).start();

            new Thread(() -> {
                while (true) {
                    String inputMessage = userMessage.nextLine();
                    if (Objects.equals("q", inputMessage)) {
                        socketWrapper.getOutput().println(inputMessage);
                        connection = false;
                        break;
                    }
                    if (Objects.equals("Exit", inputMessage) && userName.equalsIgnoreCase("Admin")) {
                        socketWrapper.getOutput().println(inputMessage);
                        connection = false;
                        break;
                    }
                    socketWrapper.getOutput().println(inputMessage);
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}