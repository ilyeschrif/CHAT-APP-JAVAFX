package com.example.serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur {
    private int port;

    public Serveur(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Serveur is waiting for a client...");

        ExecutorService executorService = Executors.newCachedThreadPool();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            executorService.execute(new ClientHandler(clientSocket));
        }
    }

    class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {

                OutputStream outputStream = clientSocket.getOutputStream();
                
                outputStream.write(vmessagesController.getSentMessage().getBytes());

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}