package ru.siberian.huawei.LogSaver.managment;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LogTimer {
//  длительность суток
    private final long LONGDAYBYSECONDS = 86400000;

    public LogTimer() {
        Date date = new MyDate().setStartTime();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//  здесь будет код для старта коннекций
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, date, LONGDAYBYSECONDS);
    }
}
