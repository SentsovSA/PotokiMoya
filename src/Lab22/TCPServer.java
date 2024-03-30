package Lab22;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class TCPServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("TCP Сервер запущен...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился клиент: " + clientSocket);

                Thread clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private Map<String, Integer> punishments;
    private Map<String, Integer> rewards;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.punishments = new HashMap<>();
        this.rewards = new HashMap<>();
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] tokens = inputLine.split(" ");
                String command = tokens[0];
                String employeeName = tokens[1];

                switch (command) {
                    case "ADD":
                        addEmployee(employeeName);
                        out.println(employeeName + " успешно добавлен");
                        break;
                    case "PUNISH":
                        punishEmployee(employeeName);
                        out.println(employeeName + " наказан");
                        break;
                    case "REWARD":
                        rewardEmployee(employeeName);
                        out.println(employeeName + " награжден");
                        break;
                    case "INFO":
                        String info = getEmployeeInfo(employeeName);
                        out.println(info);
                        break;
                    case "REMOVE_PUNISHMENT":
                        removePunishment(employeeName);
                        out.println("С "+ employeeName +  " взыскание снято");
                        break;
                    default:
                        out.println("Неизвестная команда");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void addEmployee(String employeeName) {
        punishments.put(employeeName, 0);
        rewards.put(employeeName, 0);
    }

    private synchronized void punishEmployee(String employeeName) {
        if (punishments.containsKey(employeeName)) {
            int punishmentCount = punishments.get(employeeName);
            punishments.put(employeeName, punishmentCount + 1);
        }
    }

    private synchronized void rewardEmployee(String employeeName) {
        if (rewards.containsKey(employeeName)) {
            int rewardCount = rewards.get(employeeName);
            rewards.put(employeeName, rewardCount + 1);
        }
    }

    private synchronized void removePunishment(String employeeName) {
        if (punishments.containsKey(employeeName)) {
            int punishmentCount = punishments.get(employeeName);
            if (punishmentCount > 0) {
                punishments.put(employeeName, punishmentCount - 1);
            }
        }
    }

    private synchronized String getEmployeeInfo(String employeeName) {
        if (punishments.containsKey(employeeName) && rewards.containsKey(employeeName)) {
            int punishmentCount = punishments.get(employeeName);
            int rewardCount = rewards.get(employeeName);
            return "Работник " + employeeName + " получил " + punishmentCount + " взысканий и " + rewardCount + " наград.";
        } else {
            return "Работник не найден";
        }
    }
}

