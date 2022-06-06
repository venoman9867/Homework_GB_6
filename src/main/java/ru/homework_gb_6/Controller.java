package ru.homework_gb_6;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    public Button buttonSend;
    public TextField textField;
    public TextArea textArea;

    public Client client;

    public void sendMessage() {
        String s = textArea.getText();
        textArea.setText("");
        client.sendMessage(s);
    }
}