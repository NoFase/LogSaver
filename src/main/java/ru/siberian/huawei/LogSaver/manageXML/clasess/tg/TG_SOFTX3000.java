package ru.siberian.huawei.LogSaver.manageXML.clasess.tg;

public class TG_SOFTX3000 {
    String TG;
    String TGN;
    String type;

    public String getTG() {
        return TG;
    }

    public void setTG(String TG) {
        this.TG = TG;
    }

    public String getTGN() {
        return TGN;
    }

    public void setTGN(String TGN) {
        this.TGN = TGN;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TG_SOFTX3000{" +
                "TG='" + TG + '\'' +
                ", TGN='" + TGN + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
