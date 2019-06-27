package ru.siberian.huawei.LogSaver.managment;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class MyDate {
    private SimpleDateFormat form = new SimpleDateFormat("yyyy&MM&dd hh&mm");
    private LocalDateTime localDate;


    public Date setStartTime(){
        String time[] = form.format(new Date().getTime()).split("\\s+")[0].split("&");
//        need write check when changing month, and year
        localDate = LocalDate.now().plusDays(1).atTime(1, 0);
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()); //convert LocalDateTime to Date
    }

    public String dateForCommand (long period){
        return form.format(new Date().getTime() - period).split("\\s+")[0];
    }
    public String localDate(){
        return form.format(new Date().getTime());
    }
}
