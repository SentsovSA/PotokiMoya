package Lab22;

import java.io.*;
import java.net.*;

public class TCPClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("ADD LOL");
            out.println("PUNISH LOL");
            out.println("PUNISH LOL");
            out.println("REMOVE_PUNISHMENT LOL");
            out.println("REWARD LOL");
            out.println("KEK LOL");
            out.println("INFO LOL");

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Ответ от сервера: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

