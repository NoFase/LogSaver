package ru.siberian.huawei.LogSaver.managment.tblMGTID;

import ru.siberian.huawei.LogSaver.network.ConnectToServer;

public class Communicator {

    private ConnectToServer connect = new ConnectToServer();

    private String inputLine;

    public Communicator(String serverIp) {
        connect.setServerIp(serverIp);
    }
}
