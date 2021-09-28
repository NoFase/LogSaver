package ru.siberian.huawei.LogSaver.manageXML.clasess.tkc;

public class TKC_SOFTX3000 {
    String type;
    String MN;
    String SC;
    String EC;
    String TG;
    String TID;

    public String getMN() {
        return MN;
    }

    public void setMN(String MN) {
        this.MN = MN;
    }

    public String getSC() {
        return SC;
    }

    public void setSC(String SC) {
        this.SC = SC;
    }

    public String getEC() {
        return EC;
    }

    public void setEC(String EC) {
        this.EC = EC;
    }

    public String getTG() {
        return TG;
    }

    public void setTG(String TG) {
        this.TG = TG;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TKC_SOFTX3000{" +
                "type='" + type + '\'' +
                ", MN='" + MN + '\'' +
                ", SC='" + SC + '\'' +
                ", EC='" + EC + '\'' +
                ", TG='" + TG + '\'' +
                ", TID='" + TID + '\'' +
                '}';
    }
}
