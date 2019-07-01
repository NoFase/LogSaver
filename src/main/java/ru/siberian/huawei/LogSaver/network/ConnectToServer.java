package ru.siberian.huawei.LogSaver.network;

import ru.siberian.huawei.LogSaver.managment.InputAnalyzer;

import java.io.IOException;

public class ConnectToServer extends Thread implements TCPConnectionListener{
    private TCPConnection connection;
    private InputAnalyzer analyzer;

    private final int PORT = 6000;
    private final String LGI = "LGI:op=\"bot\", PWD =\"SoftX3000\";";

    private String serverIp;

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setAnalyzer(InputAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public TCPConnection getConnection() {
        return connection;
    }

    @Override
    public void run() {
//        super.run();
        try {
            connection = new TCPConnection(this, serverIp, PORT);
            System.out.println("connection ready to server: " + serverIp);
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
        if (value == null) tcpConnection.disconnect();
        else analyzer.analysis(value, tcpConnection);
//        System.out.println(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        System.out.println("disconnected from: " + serverIp);
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
