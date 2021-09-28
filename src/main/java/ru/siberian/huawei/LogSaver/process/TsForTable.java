package ru.siberian.huawei.LogSaver.process;

public class TsForTable {
    private String cityName;
    private int numberOfStm;
    private int numberOfE1;
    private int numberOfTid;
    private int numberOfTs;
    private int numberOfCic;
    private int trunkNumber;
    private String trunkName;
    private String typeOfE1;

    public TsForTable(String cityName, int numberOfStm, int numberOfE1, int numberOfTid, int numberOfTs, int numberOfCic, int trunkNumber, String trunkName, String typeOfE1) {
        this.cityName = cityName;
        this.numberOfStm = numberOfStm;
        this.numberOfE1 = numberOfE1;
        this.numberOfTid = numberOfTid;
        this.numberOfTs = numberOfTs;
        this.numberOfCic = numberOfCic;
        this.trunkNumber = trunkNumber;
        this.trunkName = trunkName;
        this.typeOfE1 = typeOfE1;
    }

    public String getCityName() {
        return cityName;
    }

    public int getNumberOfStm() {
        return numberOfStm;
    }

    public int getNumberOfE1() {
        return numberOfE1;
    }

    public int getNumberOfTid() {
        return numberOfTid;
    }

    public int getNumberOfTs() {
        return numberOfTs;
    }

    public int getNumberOfCic() {
        return numberOfCic;
    }

    public int getTrunkNumber() {
        return trunkNumber;
    }

    public String getTrunkName() {
        return trunkName;
    }

    public String getTypeOfE1() {
        return typeOfE1;
    }

    @Override
    public String toString() {
        return "TsForTable{" +
                "cityName='" + cityName + '\'' +
                ", numberOfStm=" + numberOfStm +
                ", numberOfE1=" + numberOfE1 +
                ", numberOfTid=" + numberOfTid +
                ", numberOfTs=" + numberOfTs +
                ", numberOfCic=" + numberOfCic +
                ", trunkNumber=" + trunkNumber +
                ", trunkName='" + trunkName + '\'' +
                ", typeOfE1='" + typeOfE1 + '\'' +
                '}';
    }
}
