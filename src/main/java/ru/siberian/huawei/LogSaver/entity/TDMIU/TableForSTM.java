package ru.siberian.huawei.LogSaver.entity.TDMIU;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TableForSTM {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotNull
    private String cityName;
    @NotNull
    private int numberOfSTM;
    @NotNull
    private int numberOfE1;
    @NotNull
    private int startTid;
    @NotNull
    private int endTid;
    @NotNull
    private String klm;
    private String startTs;
    private String endTs;
    private String startCIC;
    private String endCIC;
    private String numberOfTrunk;
    private String numberOfProject;
    private String nameOfTrunk;
    @Transient
    private int numberOFFCCU;


    public TableForSTM(@NotNull String cityName, @NotNull int numberOfSTM, @NotNull int numberOfE1, @NotNull int startTid, @NotNull int endTid, @NotNull String klm) {
        this.cityName = cityName;
        this.numberOfSTM = numberOfSTM;
        this.numberOfE1 = numberOfE1;
        this.startTid = startTid;
        this.endTid = endTid;
        this.klm = klm;
    }

    public TableForSTM(@NotNull String cityName, @NotNull int numberOfSTM, @NotNull int numberOfE1, @NotNull int startTid, @NotNull int endTid, @NotNull String klm, String startTs, String endTs, String startCIC, String endCIC, String numberOfTrunk, String numberOfProject, String nameOfTrunk) {
        this.cityName = cityName;
        this.numberOfSTM = numberOfSTM;
        this.numberOfE1 = numberOfE1;
        this.startTid = startTid;
        this.endTid = endTid;
        this.klm = klm;
        this.startTs = startTs;
        this.endTs = endTs;
        this.startCIC = startCIC;
        this.endCIC = endCIC;
        this.numberOfTrunk = numberOfTrunk;
        this.numberOfProject = numberOfProject;
        this.nameOfTrunk = nameOfTrunk;
//        this.numberOFFCCU = numberOFFCCU;
    }

    @Override
    public String toString() {
        return "TableForSTM{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", numberOfSTM=" + numberOfSTM +
                ", numberOfE1=" + numberOfE1 +
                ", startTid=" + startTid +
                ", endTid=" + endTid +
                ", klm='" + klm + '\'' +
                ", startTs='" + startTs + '\'' +
                ", endTs='" + endTs + '\'' +
                ", startCIC='" + startCIC + '\'' +
                ", endCIC='" + endCIC + '\'' +
                ", numberOfTrunk='" + numberOfTrunk + '\'' +
                ", numberOfProject='" + numberOfProject + '\'' +
                ", nameOfTrunk='" + nameOfTrunk + '\'' +
                ", numberOFFCCU=" + numberOFFCCU +
                '}';
    }
}
