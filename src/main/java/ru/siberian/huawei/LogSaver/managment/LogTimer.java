package ru.siberian.huawei.LogSaver.managment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.siberian.huawei.LogSaver.controllers.SbcController;
import ru.siberian.huawei.LogSaver.entity.MessagesRepository;
import ru.siberian.huawei.LogSaver.external.ExternalData;
import ru.siberian.huawei.LogSaver.external.ListOfServers;
import ru.siberian.huawei.LogSaver.network.ConnectToSbc;
import ru.siberian.huawei.LogSaver.network.ConnectToServer;

import java.util.*;

public class LogTimer {
    private ExternalData externalData;
    private MessagesRepository repository;
    private final Logger LOGGER = LoggerFactory.getLogger(LogTimer.class);

    public LogTimer(ExternalData externalData, MessagesRepository repository) {

        this.externalData = externalData;
        this.repository = repository;

        Date date = new MyDate().setStartTime();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//  здесь будет код для старта коннекций
                for (HashMap.Entry entry: externalData.getServers().entrySet()){
                    ConnectToServer connect = new ConnectToServer();
                    connect.setServerIp(String.valueOf(entry.getKey()));
                    connect.setAnalyzer(new InputAnalyzer(externalData.getExceptions(),
                            repository,
                            String.valueOf(entry.getValue())));
                    LOGGER.info("Connection to SoftX300 " + entry.getValue() + " with IP: " + entry.getKey());
                    connect.run();
                }

                for (Map.Entry entry: ListOfServers.serversSbc.entrySet()){
                    ConnectToSbc connectToSbc = new ConnectToSbc();
                    connectToSbc.setServerIp(String.valueOf(entry.getKey()));
                    connectToSbc.setLogin("smednyh");
                    connectToSbc.setPwd("lem600@HW12");
                    LOGGER.info("Connection to Se2900 " + entry.getValue() + " with IP: " + entry.getKey());
                    connectToSbc.run();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, date, (long)InfTemplates.LONGDAYBYSECONDS);

    }
}
