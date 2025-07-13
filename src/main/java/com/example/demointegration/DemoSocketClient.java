package com.example.demointegration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class DemoSocketClient {

    public static void main(String[] args) throws Exception {
        Socket socketConnection = new Socket(InetAddress.getByName("localhost"), 1234);
        PrintWriter writer = new PrintWriter(socketConnection.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
        String textValue = "";
        Scanner scanner = new Scanner(System.in);
        while (!textValue.equalsIgnoreCase("q")) {
            System.out.print("Enter text to send: ");
            textValue = scanner.nextLine();
            if(textValue.equalsIgnoreCase("q")) {
                break;
            }
            writer.println(textValue);
            System.out.println(reader.readLine());
        }
        scanner.close();
        reader.close();
        writer.close();
        socketConnection.close();
    }
}
