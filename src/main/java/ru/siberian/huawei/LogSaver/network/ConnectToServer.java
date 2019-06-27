package ru.siberian.huawei.LogSaver.network;

import java.io.IOException;

public class ConnectToServer extends Thread implements TCPConnectionListener{
    private TCPConnection connection;

    private final int PORT = 6000;
    private final String LGI = "LGI:op=\"bot\", PWD =\"SoftX3000\";";

    private String serverIp;

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public TCPConnection getConnection() {
        return connection;
    }

    @Override
    public void run() {
        super.run();
        try {
            connection = new TCPConnection(this, serverIp, PORT);
        } catch (IOException e) {
            System.out.println("Какие-то проблемы с доступом к серверу: " + serverIp);
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        tcpConnection.sendString(LGI);
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {

    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {

    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }


    @Override
    public void interrupt() {
        connection.disconnect();
        super.interrupt();
    }
}
