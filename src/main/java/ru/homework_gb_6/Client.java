package ru.homework_gb_6;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class Client extends javafx.application.Application {
    private final String Server_ADDR="localhost";
    private final int Server_PORT=8189;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    Controller controller=new Controller();
    public Client(){
        try {
            openConnection();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void openConnection() throws IOException{
        socket=new Socket(Server_ADDR,Server_PORT);
        in=new DataInputStream(socket.getInputStream());
        out=new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true){
                    String strFromServer=in.readUTF();
                    if(strFromServer.equalsIgnoreCase("/end")){
                        break;
                    }
                    controller.textArea.appendText(strFromServer);
                    controller.textArea.appendText("\n");
                }
                closeConnection();
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
    }
    public void closeConnection(){
        try{
            in.close();
            out.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void sendMessage(String s){
        if(!s.trim().isEmpty()){
            try {
                out.writeUTF(s);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("clientChat.fxml")));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}