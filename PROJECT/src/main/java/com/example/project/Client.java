package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

    private static String message;
    private final vmessagesController controller;

    public Client(vmessagesController controller) {
        this.controller = controller;
    }

    public void run() {

        try {
            Socket socket = null;
            try {
                while (socket == null) {
                    try {
                        socket = new Socket("localhost", 1234);
                    } catch (Exception e) {
                        Thread.sleep(1000);
                    }
                }

                while (true) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                    String receivedString = "";
                    while ((receivedString = in.readLine()) != null) {
                        System.out.println(receivedString);
                        controller.receiveMessage(receivedString);
                    }
                }

            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMessage() {
        return message;
    }
}