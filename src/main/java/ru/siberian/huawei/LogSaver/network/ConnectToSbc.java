package ru.siberian.huawei.LogSaver.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.siberian.huawei.LogSaver.external.ListOfServers;
import ru.siberian.huawei.LogSaver.managment.MyDate;
import ru.siberian.huawei.LogSaver.managment.ParserValueFromSbc;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ConnectToSbc extends Thread implements TCPConnectionListener {
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectToSbc.class);
    private final int PORT = 6000;

    private TCPConnection connection;
    private String lgi;
    private String login;
    private String pwd;
    private String serverIp;
    private boolean chkLgi = false;
    private boolean chkDate = false;
    private String cityName;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
        cityName = ListOfServers.serversSbc.get(serverIp); // setup name of SBC
//        LOGGER.info("----------> name of SBC: " + cityName);
    }

    public TCPConnection getConnection() {
        return connection;
    }

    public void disconnectSbc(){
        getConnection().disconnect();
    }

    @Override
    public void run(){
        LOGGER.info("start connection to SBC, ip: " + serverIp);
        try {
            connection = new TCPConnection(this, serverIp, PORT);
            LOGGER.info("connection ready to server: " + serverIp);
        } catch (IOException e) {
            LOGGER.info("problem with connection to ip: " + serverIp);
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) throws InterruptedException {
        lgi = "lgi:op=\"" + login + "\",pwd=\"" + pwd + "\";";
//        LOGGER.info("command for logging was create: " + lgi);
        tcpConnection.sendString(lgi);
        chkLgi = true;
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) throws InterruptedException, IOException, ParseException {
        if (value == null) tcpConnection.disconnect();
        else {
            if (chkLgi && value.contains("RETCODE = 0  Operation succeeded")) {
                chkLgi = false;
                tcpConnection.sendString("DSP TIME:;");
                chkDate = true;
                LOGGER.info("Logging to " + serverIp + " successful!");
            }
            if (chkDate && value.contains("Current time of system  =")){
                chkDate = false;
                String line = value.split("\\s{2,}")[2];

                Date dateStart = new MyDate().convertingStringToDate(line);
                LocalDateTime localDateTime = dateStart.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                tcpConnection.sendString("LST OPTLOG: BEGQRYTM=" + new MyDate()
                        .convertLocalDateTimeToString(localDateTime.minusDays(1)) + ";");
            }

            new ParserValueFromSbc(value, cityName);
        }
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        LOGGER.info("Disconnected from " + serverIp);
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
    }
}
