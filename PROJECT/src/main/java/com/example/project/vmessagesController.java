package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class vmessagesController {



    @FXML
    private static TextField inputField;

    @FXML
    private Button sendButton;

    @FXML
    public TextArea messagesArea;


    @FXML
    protected void sendMessage() {
        String message = inputField.getText();
        if (message != null && !message.isEmpty()) {
            messagesArea.appendText("Client: " + message + "\n");
            inputField.clear();
        }
    }

    @FXML
    protected void receiveMessage(String msg) throws IOException {

        if (msg != null && !msg.isEmpty()) {
            messagesArea.appendText("\n"+"Serveur: " + msg);
        }

    }
}