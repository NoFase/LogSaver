package ru.siberian.huawei.LogSaver.managment;

import ru.siberian.huawei.LogSaver.entity.Messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.siberian.huawei.LogSaver.LogSaverApplication.repos;

public class FilterBd {
    private String abrCity;
    private String dateStart;
    private String dateEnd;
    private String sign;
    private List<Messages> messages;
    private List<String> searcher;

    public FilterBd(String abrCity, String dateStart, String dateEnd, String sign) {
        this.abrCity = abrCity.toUpperCase();
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.sign = sign;
    }

    public List<String> searching() {
        searcher = new ArrayList<>();
        if (abrCity.length() > 1) {
            System.out.println(abrCity + "--------");
            if (dateStart.length() > 1 && dateEnd.length() > 1) {
                Date dateStartD = new MyDate().convertingStringToDateWithoutTime(dateStart);
                Date dateEndD = new MyDate().convertingStringToDateWithoutTime(dateEnd);
                if (sign.length() > 1) {
                    messages = repos.findAny(abrCity, dateStartD, dateEndD, sign);
                } else {
                    messages = repos.findWithoutCommand(abrCity, dateStartD, dateEndD);
                }
            } else messages = repos.findOnlyCity(abrCity);



            for (int i = 0; i < messages.size(); i++) {
                searcher.add(messages.get(i).getDate() + "\t" + messages.get(i).getCityName() + "\t"
                        + messages.get(i).getIp() + "\t" + messages.get(i).getLogin() + "\t" + messages.get(i).getCommand() + "\n");
            }
        } else {
            String add = "Необходимо заполнить хотябы город!";
            searcher.add(add);
            System.out.println(searcher.size());
        }

        return searcher;
    }
}
