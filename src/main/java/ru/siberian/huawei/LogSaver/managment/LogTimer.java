package ru.siberian.huawei.LogSaver.managment;

import ru.siberian.huawei.LogSaver.entity.MessagesRepository;
import ru.siberian.huawei.LogSaver.external.ExternalData;
import ru.siberian.huawei.LogSaver.network.ConnectToServer;

import java.util.*;

public class LogTimer {
//  длительность суток
    private final long LONGDAYBYSECONDS = 86400000;
//    private final long LONGDAYBYSECONDS = 10000;// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!for debug
    private ExternalData externalData;
    private MessagesRepository repository;

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
                    connect.run();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, date, LONGDAYBYSECONDS);
    }
}
