package ru.siberian.huawei.LogSaver.manageXML.clasess.tkc;

public class V5TKC_SOFTX3000 extends TKC_SOFTX3000{
    private String EEN;
    private String SEN;
    private String EN;
    private String LNKS;
    private String MGEID;
    private String NLNKID;
    private String NTGID;
    private String NTID;
    private String SLNKID;
    private String SMGEID;
    private String TGID;

    private String type = "V5";

    {
        super.type = this.type;
    }

    @Override
    public String getTG(){
        return this.TGID;
    }

    @Override
    public String getSC(){
        int sc = 0;
        if (this.SEN != null) {
            sc = Integer.parseInt(this.SEN) * 32;
        }
        return String.valueOf(sc);
    }

    @Override
    public String getEC(){
        int ec = 0;
        if (this.EEN != null) {
            ec = Integer.parseInt(this.EEN) * 32 + 31;
        }
        return String.valueOf(ec);
    }

    public String getEEN() {
        return EEN;
    }

    public void setEEN(String EEN) {
        this.EEN = EEN;
    }

    public String getSEN() {
        return SEN;
    }

    public void setSEN(String SEN) {
        this.SEN = SEN;
    }

    public String getEN() {
        return EN;
    }

    public void setEN(String EN) {
        this.EN = EN;
    }

    public String getLNKS() {
        return LNKS;
    }

    public void setLNKS(String LNKS) {
        this.LNKS = LNKS;
    }

    public String getMGEID() {
        return MGEID;
    }

    public void setMGEID(String MGEID) {
        this.MGEID = MGEID;
    }

    public String getNLNKID() {
        return NLNKID;
    }

    public void setNLNKID(String NLNKID) {
        this.NLNKID = NLNKID;
    }

    public String getNTGID() {
        return NTGID;
    }

    public void setNTGID(String NTGID) {
        this.NTGID = NTGID;
    }

    public String getNTID() {
        return NTID;
    }

    public void setNTID(String NTID) {
        this.NTID = NTID;
    }

    public String getSLNKID() {
        return SLNKID;
    }

    public void setSLNKID(String SLNKID) {
        this.SLNKID = SLNKID;
    }

    public String getSMGEID() {
        return SMGEID;
    }

    public void setSMGEID(String SMGEID) {
        this.SMGEID = SMGEID;
    }

    public String getTGID() {
        return TGID;
    }

    public void setTGID(String TGID) {
        this.TGID = TGID;
    }
}
