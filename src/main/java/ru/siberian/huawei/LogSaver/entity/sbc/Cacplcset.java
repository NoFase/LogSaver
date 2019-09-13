package ru.siberian.huawei.LogSaver.entity.sbc;

public class Cacplcset {

    private String nameCacplcsetname;
    private String cacplcsetmode;
    private String callplcname;

    public Cacplcset(String nameCacplcsetname, String cacplcsetmode, String callplcname) {
        this.nameCacplcsetname = nameCacplcsetname;
        this.cacplcsetmode = cacplcsetmode;
        this.callplcname = callplcname;
    }

    public String getNameCacplcsetname() {
        return nameCacplcsetname;
    }

    public void setNameCacplcsetname(String nameCacplcsetname) {
        this.nameCacplcsetname = nameCacplcsetname;
    }

    public String getCacplcsetmode() {
        return cacplcsetmode;
    }

    public void setCacplcsetmode(String cacplcsetmode) {
        this.cacplcsetmode = cacplcsetmode;
    }

    public String getCallplcname() {
        return callplcname;
    }

    public void setCallplcname(String callplcname) {
        this.callplcname = callplcname;
    }
}
