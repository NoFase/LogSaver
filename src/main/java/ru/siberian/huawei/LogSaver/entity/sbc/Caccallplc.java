package ru.siberian.huawei.LogSaver.entity.sbc;

public class Caccallplc {

    private String nameCacllplcname;
    private String msn;
    private String mcnt;
    private String mcr;

    public Caccallplc(String nameCacllplcname, String msn, String mcnt, String mcr) {
        this.nameCacllplcname = nameCacllplcname;
        this.msn = msn;
        this.mcnt = mcnt;
        this.mcr = mcr;
    }

    public String getNameCacllplcname() {
        return nameCacllplcname;
    }

    public void setNameCacllplcname(String nameCacllplcname) {
        this.nameCacllplcname = nameCacllplcname;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getMcnt() {
        return mcnt;
    }

    public void setMcnt(String mcnt) {
        this.mcnt = mcnt;
    }

    public String getMcr() {
        return mcr;
    }

    public void setMcr(String mcr) {
        this.mcr = mcr;
    }
}
