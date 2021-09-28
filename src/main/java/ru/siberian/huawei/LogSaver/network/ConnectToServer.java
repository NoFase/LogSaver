package ru.siberian.huawei.LogSaver.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.siberian.huawei.LogSaver.managment.InputAnalyzer;

import java.io.IOException;

public class ConnectToServer extends Thread implements TCPConnectionListener{
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectToServer.class);
    private TCPConnection connection;
    private InputAnalyzer analyzer;
    private int check = 0;

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
        try {
            LOGGER.info("start connection to server: " + serverIp);
            connection = new TCPConnection(this, serverIp, PORT);
            LOGGER.info("connection ready to server: " + serverIp);
        } catch (IOException e) {
            LOGGER.warn("Какие-то проблемы с доступом к серверу: " + serverIp);
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection)  {
        tcpConnection.sendString(LGI);
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) throws IOException{
//        LOGGER.info(">>>>" + value); //only for debug, before product need comment
        if (value == null) {
            tcpConnection.disconnect();
        }
        else {
//            LOGGER.info(value);
            analyzer.analysis(value, tcpConnection);
        }
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        LOGGER.info("disconnected from: " + serverIp);
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }


    @Override
    public void interrupt() {
        LOGGER.info("Connection to server " + serverIp + " was interrupt!");
        connection.disconnect();
        super.interrupt();
    }
}
