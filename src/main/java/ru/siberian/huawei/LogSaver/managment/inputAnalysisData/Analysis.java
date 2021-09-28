package ru.siberian.huawei.LogSaver.managment.inputAnalysisData;

import ru.siberian.huawei.LogSaver.network.TCPConnection;

public class Analysis {

    private String typeOfAnalysis;
    private TCPConnection tcpConnection;

    public TCPConnection getTcpConnection() {
        return tcpConnection;
    }

    public Boolean checkingEnd(String line){
        if (line.contains("---    END")) return false;
        return true;
    }
}
