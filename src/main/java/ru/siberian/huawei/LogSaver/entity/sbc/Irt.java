package ru.siberian.huawei.LogSaver.entity.sbc;

import java.util.List;

public class Irt implements Commandared{
//    only for static routing between some ISIPTG
    private final String RTTYPE = "STATIC";

    private String rtName;
//    List of office direction names
    private List<String> ofcNames;
//    Outbound trunk selection name
    private String snot;

    public Irt(String rtName, List<String> ofcNames, String snot) {
        if (rtName.length() < 31) this.rtName = rtName;
//        !!!!!!!!!!!!! NEED OUTPUT ALARM ABOUT NOT CORRECT DATA
        else System.out.println("ERROR name length");
        this.ofcNames = ofcNames;
//        this.pOfcList = pOfcList;
        this.snot = snot;
    }

    public String getRtName() {
        return rtName;
    }

    public List<String> getOfcNames() {
        return ofcNames;
    }

    public String getSnot() {
        return snot;
    }

    @Override
    public String getCommand() {
        String outputCommand = String.format("ADD IRT: RTNAME=\"%s\", RTTYPE=%s, ", rtName, RTTYPE);
        if (ofcNames.size() != 0)
            for (int i = 0; i < ofcNames.size(); i++) {
                int j = i + 1;
                int priority = 100 + i * 10;
                outputCommand += "OFC" + j + "NAME=\"" + ofcNames.get(i) + "\", POFC" + j + "=" + priority + ", ";
            }
        outputCommand += "SNOT=\"" + snot + "\";";
        return outputCommand;
    }
}
