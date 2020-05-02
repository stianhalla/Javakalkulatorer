package annet.oppgaver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(ConnectionConfig.PORT);


        Socket client = serverSocket.accept();
        System.out.println("klient tilkoblet");

        while (true) {
            new Thread(new ClientHandler(client)).start();
        }

    }
}


class ClientHandler implements Runnable {
    DataInputStream inputStream;
    DataOutputStream outputStream;

    public ClientHandler(Socket socket) {
        try {
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println(inputStream.readInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}