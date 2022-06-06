package ru.homework_gb_6;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Socket socket;
        try(ServerSocket serverSocket= new ServerSocket(8189)){
            System.out.println("Сервер запущен! Ожидаем подключение...");
            socket=serverSocket.accept();
            System.out.println("Клиент подключился!");
            DataInputStream in= new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            while (true){
                String str=in.readUTF();
                if(str.equals("/end")){
                    break;
                }
                out.writeUTF("Сервер: "+str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
