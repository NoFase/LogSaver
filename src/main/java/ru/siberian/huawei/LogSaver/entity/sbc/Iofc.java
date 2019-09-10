package ru.siberian.huawei.LogSaver.entity.sbc;

public class Iofc implements Commandared{
//    Only for situation when we are create IOFC only for one ISIPTG
//    Office direction name
    private String ofcName;
//    Name of 1st trunk group
    private String tg1Name;

    public Iofc(String ofcName, String tg1Name) {
        if (ofcName.length() < 31) this.ofcName = ofcName;
//        !!!!!!!!!!!!! NEED OUTPUT ALARM ABOUT NOT CORRECT DATA
        else System.out.println("ERROR name length");
        this.tg1Name = tg1Name;
    }

    public String getOfcName() {
        return ofcName;
    }

    public String getTg1Name() {
        return tg1Name;
    }

    @Override
    public String getCommand() {
        return String.format("ADD IOFC: OFCNAME=\"%s\", TG1NAME=\"%s\";",
                ofcName, tg1Name);
    }
}
