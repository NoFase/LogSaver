package ru.siberian.huawei.LogSaver.entity;

//import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Entity
@Getter
//@Setter
@Table(name="sbclogmessages")
public class SbcLogMessages {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String typeOfInterface;
    private String operator;
    private String ip;
    private Date beginTime;
    private Date endTime;
    private String commandName;
    private int numberOfMe;
    private String result;
    private String info;

    @Column(length = 10000)
    private String description;

    @Column(name = "CITY_NAME")
    private String cityName;

    public SbcLogMessages() {
    }

    public SbcLogMessages( String typeOfInterface, String operator, String ip, Date beginTime, Date endTime, String commandName, int numberOfMe, String result, String info, String description, String cityName) {
//        this.id = id;
        this.typeOfInterface = typeOfInterface;
        this.operator = operator;
        this.ip = ip;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.commandName = commandName;
        this.numberOfMe = numberOfMe;
        this.result = result;
        this.info = info;
        this.description = description;
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return
//                "id=" + id +
                "\n\ttypeOfInterface='" + typeOfInterface + '\'' +
                "\n\toperator='" + operator + '\'' +
                "\n\tip='" + ip + '\'' +
                "\n\tbeginTime='" + beginTime + '\'' +
                "\n\tendTime='" + endTime + '\'' +
                "\n\tcommandName='" + commandName + '\'' +
                "\n\tnumberOfMe=" + numberOfMe +
                "\n\tresult='" + result + '\'' +
                "\n\tinfo='" + info + '\'' +
                "\n\tdescription='" + description + '\'' +
                "\n\tcityName=" + cityName+ "\n";
    }
}
