package ru.siberian.huawei.LogSaver.managment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class MyDate {
    private SimpleDateFormat form = new SimpleDateFormat("yyyy&MM&dd hh&mm");
    private LocalDateTime localDate;

//      yyyy-MM-dd HH:mm:ss+GMC     items[5] + items[6]
    public Date setStartTime(){
        String time[] = form.format(new Date().getTime()).split("\\s+")[0].split("&");
//        need write check when changing month, and year
//        в следующий раз когда будешь дебажить обращай внимание на plusDays() для продакта должен быть = 1, для тестов 0
        localDate = LocalDate.now().plusDays(InfTemplates.plusDays).atTime(InfTemplates.hour, InfTemplates.minutes);
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()); //convert LocalDateTime to Date
    }

    public String dateForCommand (long period){
        return form.format(new Date().getTime() - period).split("\\s+")[0];
    }
    public String localDate(){
        return form.format(new Date().getTime());
    }

    public Date convertingStringToDate(String stringTime){
        DateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (stringTime.length() > 20) stringTime = stringTime.substring(0, 19);
        try {
            return form.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public Date convertingStringToDateWithoutTime(String stringTime){
        DateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        if (stringTime.length() > 20) stringTime = stringTime.substring(0, 19);
        try {
            return form.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public String convertLocalDateTimeToString(LocalDateTime localDateTime){
        return localDateTime.getYear() + "&"
                + localDateTime.getMonthValue() + "&"
                + localDateTime.getDayOfMonth() + "&"
                + localDateTime.getHour() + "&"
                + localDateTime.getMinute() + "&"
                + localDateTime.getSecond();
    }
}
