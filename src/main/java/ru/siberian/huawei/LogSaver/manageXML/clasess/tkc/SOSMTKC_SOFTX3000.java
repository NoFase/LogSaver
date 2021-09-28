package ru.siberian.huawei.LogSaver.manageXML.clasess.tkc;

public class SOSMTKC_SOFTX3000 extends TKC_SOFTX3000{
    private String CS;
    private String EN;
    private String STG;
    private String TG;
    private String type = "SOSM";

    {
        super.type = this.type;
    }


    @Override
    public String getTG() {
        TG = this.STG;
        return TG;
    }

    public String getCS() {
        return CS;
    }

    public void setCS(String CS) {
        this.CS = CS;
    }

    public String getEN() {
        return EN;
    }

    public void setEN(String EN) {
        this.EN = EN;
    }

    public String getSTG() {
        return STG;
    }

    public void setSTG(String STG) {
        this.STG = STG;
    }

}
