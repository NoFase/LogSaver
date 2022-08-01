package ru.siberian.huawei.LogSaver.network;

import java.io.IOException;
import java.text.ParseException;

public interface TCPConnectionListener {
    void onConnectionReady(TCPConnection tcpConnection) throws InterruptedException;
    void onReceiveString(TCPConnection tcpConnection, String value) throws InterruptedException, IOException, ParseException;
    void onDisconnect(TCPConnection tcpConnection);
    void onException(TCPConnection tcpConnection, Exception e);
}
