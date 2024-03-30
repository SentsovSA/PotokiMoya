package Lab21;

import java.io.*;
import java.net.*;

public class UDPClient {
    private static final int SERVER_PORT = 9876;
    private static final String SERVER_HOST = "localhost";
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);

            String filename = "/Users/user/IdeaProjects/PotokiMoya/src/Lab21/example.txt";
            byte[] sendData = filename.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);
            System.out.println("Запрос отправлен на сервер");

            byte[] receiveData = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String fileContent = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Ответ от сервера:\n" + fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

