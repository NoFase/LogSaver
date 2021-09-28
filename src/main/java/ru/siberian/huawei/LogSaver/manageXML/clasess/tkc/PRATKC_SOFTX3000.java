package ru.siberian.huawei.LogSaver.manageXML.clasess.tkc;

public class PRATKC_SOFTX3000 extends TKC_SOFTX3000{
    private String CS;
    private String TIDPFX;

    private String type = "PRA";

    {
        super.type = this.type;
    }

    public String getCS() {
        return CS;
    }

    public void setCS(String CS) {
        this.CS = CS;
    }

    public String getTIDPFX() {
        return TIDPFX;
    }

    public void setTIDPFX(String TIDPFX) {
        this.TIDPFX = TIDPFX;
    }
}
