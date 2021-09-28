package ru.siberian.huawei.LogSaver.managment;

public class CheckerFinishingCommand {
    private Boolean cLGI = true;
    private Boolean cSG = true;
    private Boolean cTG = true;
    private Boolean cDetailTg = true;
    private Boolean cMGTID = true;

    public Boolean getcLGI() {
        return cLGI;
    }

    public void setcLGI(Boolean cLGI) {
        this.cLGI = cLGI;
    }

    public Boolean getcSG() {
        return cSG;
    }

    public void setcSG(Boolean cSG) {
        this.cSG = cSG;
    }

    public Boolean getcMGTID() {
        return cMGTID;
    }

    public void setcMGTID(Boolean cMGTID) {
        this.cMGTID = cMGTID;
    }

    public Boolean getcTG() {
        return cTG;
    }

    public void setcTG(Boolean cTG) {
        this.cTG = cTG;
    }

    public Boolean getcDetailTg() {
        return cDetailTg;
    }

    public void setcDetailTg(Boolean cDetailTg) {
        this.cDetailTg = cDetailTg;
    }
}
