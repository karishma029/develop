package org.example;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 5000);

        System.out.println("Connected to Server!");

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
                    System.out.println("\nServer: " + msg);
                    System.out.print("You: ");
                }
            } catch (Exception e) {
                System.out.println("Server Disconnected.");
            }
        });

        // Send Messages
        Thread send = new Thread(() -> {
            try {
                while (true) {
                    System.out.print("meee: ");
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