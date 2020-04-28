package com.prodadimhaski.eastory2.serverUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private static String ADDRESS = "";
    private static int PORT = 7700;

    private boolean connected = false;
    private Net net;

    private String serverName;
    private String userName;

    private Socket socket;
    private Thread connectingRun;

    public Client(String serverName, String userName) throws IOException, SocketException {
        this.serverName = serverName;
        this.userName = userName;
        net = new Net(PORT);

        connected = net.openConnection(ADDRESS);
        if (!connected) {
            System.out.println("Connection failed..");
        }

        socket = new Socket(InetAddress.getByName(ADDRESS),PORT);
        InputStream inputStream = socket.getInputStream();
        connectingRun = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    inputStream.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void createSession(){
        String request = "/cr/"+serverName;
        net.send(request.getBytes());
        String message = net.receive();
        message = message.substring(0,message.lastIndexOf("%"));
        System.out.println(message);
    }


    public void getSessionList(){
        String connectionPacket = "/ls/";
        net.send(connectionPacket.getBytes());
        String message = net.receive();
        message = message.substring(0,message.lastIndexOf("%"));
        System.out.println(message);

    }

    public void joinToSession(){
        String connectionPacket = "/ad/"+serverName+"%"+userName+"%";
        net.send(connectionPacket.getBytes());
        String message = net.receive();
        message = message.substring(0,message.lastIndexOf("%"));
        System.out.println(message);
    }

    public void sendQuestion(String questions){
        String request = "/lt/"+serverName+"%"+questions;
        net.send(request.getBytes());
        String message = net.receive();
        message = message.substring(0,message.lastIndexOf("%"));
        System.out.println(message);
    }

    public void sendResult(String result){
        String request = "/re/"+serverName+"%"+userName+result+"%";
        net.send(request.getBytes());
    }

}
