package org.example;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(5000);

        System.out.println("Server Started...");
        System.out.println("Waiting for Client...");

        Socket socket = serverSocket.accept();

        System.out.println("Client Connected!");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in));

        // Receive Messages
        Thread receive = new Thread(() -> {
            try {
                while (true) {
                    String msg = in.readLine();
                    System.out.println("\nClient: " + msg);
                    System.out.print("You: ");
                }
            } catch (Exception e) {
                System.out.println("Client Disconnected.");
            }
        });

        // Send Messages
        Thread send = new Thread(() -> {
            try {
                while (true) {
                    System.out.print("You: ");
                    String msg = console.readLine();
                    out.println(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        receive.start();
        send.start();
    }
}