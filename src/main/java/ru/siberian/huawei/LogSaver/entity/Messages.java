package ru.siberian.huawei.LogSaver.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Messages {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String cityName;

    private String login;

    private String ip;

    @Column(length = 1000)
    private String command;

    private Date date;

    public Messages() {
    }

    public Messages(String cityName, Date date, String login, String ip, String command) {
        this.cityName = cityName;
        this.date = date;
        this.login = login;
        this.ip = ip;
        this.command = command;
    }
}
