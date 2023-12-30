package com.example.serveur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
public class vmessagesController {

    @FXML
    private TextField inputField;

    @FXML
    private Button sendButton;

    @FXML
    private TextArea messagesArea;

    private static String sentMessage;

    @FXML
    protected void sendMessage() throws IOException {
        String message = inputField.getText();

        if (message != null && !message.isEmpty()) {
            messagesArea.appendText("\n"+"                                                                  "+"Serveur: " + message );
            inputField.clear();
            this.sentMessage=message;

            new Thread(() -> {
                Serveur serveur = new Serveur(1234);
                try {
                    serveur.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        } else {
            messagesArea.appendText("Error: Please enter a message.\n");
        }


    }


    public static String getSentMessage()
    {
        return sentMessage;
    }
}