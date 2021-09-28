package ru.siberian.huawei.LogSaver.entity.TDMIU;

import java.util.HashMap;

public class TG {
    private int numberTG;
    private String nameOfTG;
    private String typeTG;
    private int numberCSC;
    private String idSg;
    private String sSG;
    private HashMap<Integer, Integer> tkcToTid;
    private HashMap<Integer, Integer> tkcToCic;

    public TG() {
    }

    public TG(int numberTG, String nameOfTG, String typeTG) {
        this.numberTG = numberTG;
        this.nameOfTG = nameOfTG;
        this.typeTG = typeTG;
    }

    public int getNumberTG() {
        return numberTG;
    }

    public String getNameOfTG() {
        return nameOfTG;
    }

    public String getTypeTG() {
        return typeTG;
    }

    public int getNumberCSC() {
        return numberCSC;
    }

    public String getIdSg() {
        return idSg;
    }

    public HashMap<Integer, Integer> getTkcToTid() {
        return tkcToTid;
    }

    public HashMap<Integer, Integer> getTkcToCic() {
        return tkcToCic;
    }

    public void setNumberCSC(int numberCSC) {
        this.numberCSC = numberCSC;
    }

    public void setIdSg(String idSg) {
        this.idSg = idSg;
    }

    public void setTkcToTid(HashMap<Integer, Integer> tkcToTid) {
        this.tkcToTid = tkcToTid;
    }

    public void setTkcToCic(HashMap<Integer, Integer> tkcToCic) {
        this.tkcToCic = tkcToCic;
    }

    public String getsSG() {
        return sSG;
    }

    public void setsSG(String sSG) {
        this.sSG = sSG;
    }

    @Override
    public String toString() {
        return "TG{" +
                "numberTG=" + numberTG +
                ", nameOfTG='" + nameOfTG + '\'' +
                ", typeTG='" + typeTG + '\'' +
                ", numberCSC=" + numberCSC +
                ", idSg='" + idSg + '\'' +
                ", sSG='" + sSG + '\'' +
                ", tkcToTid=" + tkcToTid +
                ", tkcToCic=" + tkcToCic +
                '}';
    }
}
