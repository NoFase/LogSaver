package ru.siberian.huawei.LogSaver.service;

import ru.siberian.huawei.LogSaver.manageXML.clasess.tg.TG_SOFTX3000;
import ru.siberian.huawei.LogSaver.manageXML.clasess.tkc.N7TKC_SOFTX3000;
import ru.siberian.huawei.LogSaver.manageXML.clasess.tkc.TKC_SOFTX3000;

import java.util.HashMap;

public class LinesOfTableForTDIMU {
    private String nameOfCity;

    private int numberOfE1;
    private int numberOfSTM;
    private String typeOfSTM;
    private HashMap<String, TKC_SOFTX3000> tkc;
    private HashMap<String, TG_SOFTX3000> tgs;

    private int startTid;
    private int endTid;
    private String startTs = "";
    private String endTs = "";
    private String startCic = "";
    private String endCic = "";
    private String numberOfTrunkGroup = "";
    private String numberOfProject;
    private String nameOfTrunk = "";

    public LinesOfTableForTDIMU(int numberOfE1, int numberOfSTM, String typeOfSTM,
                                HashMap<String, TKC_SOFTX3000> tkc, HashMap<String, TG_SOFTX3000> tgs) {
        this.numberOfE1 = numberOfE1;
        this.numberOfSTM = numberOfSTM;
        this.typeOfSTM = typeOfSTM;
        this.tkc = tkc;
        this.tgs = tgs;
    }

    public LinesOfTableForTDIMU(String nameOfCity, int numberOfE1, int numberOfSTM, String typeOfSTM, int startTid,
                                int endTid, String startTs, String endTs, String startCic, String endCic,
                                String numberOfTrunkGroup, String numberOfProject, String nameOfTrunk) {
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
        if (numberOfE1 == 0)
        return      "<tr class=\"color1\">"
                            + "<th rowspan=\"2\">№ E1</th>"
                            + "<th colspan=\"3\">UMG</th>"
                            + "<th colspan=\"2\">FCCU</th>"
                            + "<th colspan=\"2\">CIC</th>"
                            + "<th rowspan=\"2\">№ Tg</th>"
                            + "<th rowspan=\"2\">1c/OFM</th>"
                            + "<th rowspan=\"2\">Name</th>"
                        + "</tr>"
                        + "<tr class=\"color1\">"
                            + "<td class=\"first\">Start TID</td>"
                            + "<td class=\"first\">End TID</td>"
                            + "<td class=\"first\">KLM</td>"
                            + "<td class=\"first\">Start TS</td>"
                            + "<td class=\"first\">End TS</td>"
                            + "<td class=\"first\">Start CIC</td>"
                            + "<td class=\"first\">End CIC</td>"
                        + "</tr>"
                        + "<tr>" +
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
        else return "<tr>" +
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
    }

    private void initialisating(){
        nameOfCity = "SRT";
        startTid = numberOfSTM * 64 *32 + numberOfE1 * 32;
        endTid = startTid + 31;
        if (tkc.get(String.valueOf(startTid)) != null) {
            startTs = tkc.get(String.valueOf(startTid)).getSC();
            endTs = String.valueOf(Integer.parseInt(startTs) + 31);
            if((tkc.get(String.valueOf(startTid)).getClass().getName()).contains("N7TKC_SOFTX3000")) {
                N7TKC_SOFTX3000 n7tkc = (N7TKC_SOFTX3000) tkc.get(String.valueOf(startTid));
                startCic = n7tkc.getSCIC();
                endCic = String.valueOf(Integer.parseInt(startCic) + 31);
            } else {
                startCic = endCic = tkc.get(String.valueOf(startTid)).getType();
            }
            numberOfTrunkGroup = tkc.get(String.valueOf(startTid)).getTG();
        }

        try {
            nameOfTrunk = tgs.get(numberOfTrunkGroup).getTGN();
        } catch (NullPointerException e) {}

    }
}