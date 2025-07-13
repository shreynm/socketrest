package com.example.demointegration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class DemoSocketClient {

    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("localhost");
        boolean isConnected = false;
        do {
            try (Socket socketConnection = new Socket(address, 1234);
                    PrintWriter writer = new PrintWriter(socketConnection.getOutputStream(), true);
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socketConnection.getInputStream()));
                    Scanner scanner = new Scanner(System.in)) {
                System.out.println("Connected with " + socketConnection.getInetAddress().getHostAddress() + ":"
                        + socketConnection.getPort());
                String textValue = "";
                while (!textValue.equalsIgnoreCase("q")) {
                    System.out.print("Enter text: ");
                    textValue = scanner.nextLine();
                    if (textValue.equalsIgnoreCase("q")) {
                        break;
                    }
                    writer.println(textValue);
                    System.out.println("Message from server: " + reader.readLine());
                }
                isConnected = true;
                writer.close();
                reader.close();
                scanner.close();
                socketConnection.close();
            } catch (SocketException e) {
                System.err.println("Connection Failed, Reconnecting......");
                Thread.sleep(10000);
                isConnected = false;
            }
        } while (!isConnected);

    }
}
