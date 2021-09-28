package ru.siberian.huawei.LogSaver.manageXML.clasess.tkc;

public class N7TKC_SOFTX3000 extends TKC_SOFTX3000{
    private String CS;
    private String ECIC;
    private String MD;
    private String SCF;
    private String SCIC;
    private String SEN;
    private String TIDPFX;

    private String type = "SS7";

    {
        super.type = this.type;
    }

    public String getCS() {
        return CS;
    }

    public void setCS(String CS) {
        this.CS = CS;
    }

    public String getECIC() {
        return ECIC;
    }

    public void setECIC(String ECIC) {
        this.ECIC = ECIC;
    }

    public String getMD() {
        return MD;
    }

    public void setMD(String MD) {
        this.MD = MD;
    }

    public String getSCF() {
        return SCF;
    }

    public void setSCF(String SCF) {
        this.SCF = SCF;
    }

    public String getSCIC() {
        return SCIC;
    }

    public void setSCIC(String SCIC) {
        this.SCIC = SCIC;
    }

    public String getSEN() {
        return SEN;
    }

    public void setSEN(String SEN) {
        this.SEN = SEN;
    }

    public String getTIDPFX() {
        return TIDPFX;
    }

    public void setTIDPFX(String TIDPFX) {
        this.TIDPFX = TIDPFX;
    }
}
