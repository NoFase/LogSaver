package ru.siberian.huawei.LogSaver.service;

public class LinesOfTableForTDIMU {
    private String nameOfCity;

    private int numberOfE1;
    private int numberOfSTM;
    private String typeOfSTM;

    private int startTid;
    private int endTid;
    private String startTs;
    private String endTs;
    private String startCic;
    private String endCic;
    private int numberOfTrunkGroup;
    private String numberOfProject;
    private String nameOfTrunk;

    public LinesOfTableForTDIMU(int numberOfE1, int numberOfSTM, String typeOfSTM) {
        this.numberOfE1 = numberOfE1;
        this.numberOfSTM = numberOfSTM;
        this.typeOfSTM = typeOfSTM;
    }

    public LinesOfTableForTDIMU(String nameOfCity, int numberOfE1, int numberOfSTM, String typeOfSTM, int startTid, int endTid, String startTs, String endTs, String startCic, String endCic, int numberOfTrunkGroup, String numberOfProject, String nameOfTrunk) {
        this.nameOfCity = nameOfCity;
        this.numberOfE1 = numberOfE1;
        this.numberOfSTM = numberOfSTM;
        this.typeOfSTM = typeOfSTM;
        this.startTid = startTid;
        this.endTid = endTid;
        this.startTs = startTs;
        this.endTs = endTs;
        this.startCic = startCic;
        this.endCic = endCic;
        this.numberOfTrunkGroup = numberOfTrunkGroup;
        this.numberOfProject = numberOfProject;
        this.nameOfTrunk = nameOfTrunk;
    }

    public String creatingLineOfTableForTDIMU(){
        initialisating();
//        String string = "";
        return "<tr>" +
                "<td>" + numberOfE1 + "</td>" +
                "<td>" + startTid + "</td>" +
                "<td>" + endTid + "</td>"+
                "<td>" +
                new KLM(typeOfSTM, numberOfE1).getKlm() +
                "</td>" +
                "<td>" + startTs + "</td>" +
                "<td>" + endTs + "</td>" +
                "<td>" + startCic + "</td>" +
                "<td>" + endCic + "</td>" +
                "<td>" + numberOfTrunkGroup + "</td>" +
                "<td>" + numberOfProject + "</td>" +
                "<td>" + nameOfTrunk + "</td>"
        ;
//        return string;
    }

    private void initialisating(){
        nameOfCity = "SRT";
        startTid = numberOfSTM * 64 *32 + numberOfE1 * 32;
        endTid = startTid + 31;
        startTs = "01";
        endTs = "30";
        startCic = "";
        endCic = "";
        numberOfTrunkGroup = (int)(Math.random()*100);
        numberOfProject = String.valueOf((int)(Math.random()*1000000));
        nameOfTrunk = "Mangoooooooo";
    }
}
