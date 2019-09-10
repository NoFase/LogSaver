package ru.siberian.huawei.LogSaver.entity.sbc;

import java.util.List;
import java.util.regex.Pattern;

public class Isiptg implements Commandared{

    private final String LINKINFO = "UDP";
    private final String PIPTYPE = "IPV4";

//    data to be specified
    private String tgName;
//    Local port number
    private String lport;
//    Peer IPv4 address
    private String pipV4;
//    Peer port number
    private String pport;


//    data to be selected
//    Local address name
    private String laddrn;
    private String meddn;
//    Heartbeat detection
    private CHB chb;
//    Inbound trunk route name
    private String rnit;
//    CAC policy set name (max 3)
    private List<String> cacPlcSetNameList;
//    InboundHMR policy set ID
    private String ipSetId;
//    Outbound HMR policy set ID
    private String opSetId;
//    List ignored ports for inbound trunk (Y/N)
    private QRYITNPORT qryitnport;

    private enum CHB {
        DISHB,
        CTHB,
        HRHB,
        SRVHB
    }

    private enum QRYITNPORT{
        Y,
        N
    }

    private String regexIp = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    public Isiptg(String tgName, String lport, String pipV4, String pport) {
        this.tgName = tgName;
        this.lport = lport;
        if (Pattern.matches(regexIp, pipV4)) this.pipV4 = pipV4;
        else System.out.println("ERROR ip address");
        this.pport = pport;
    }

    public String getTgName() {
        return tgName;
    }

    public String getLport() {
        return lport;
    }

    public String getPipV4() {
        return pipV4;
    }

    public String getPport() {
        return pport;
    }

    public String getLaddrn() {
        return laddrn;
    }

    public String getMeddn() {
        return meddn;
    }

    public CHB getChb() {
        return chb;
    }

    public String getRnit() {
        return rnit;
    }

    public List<String> getCacPlcSetNameList() {
        return cacPlcSetNameList;
    }

    public String getIpSetId() {
        return ipSetId;
    }

    public String getOpSetId() {
        return opSetId;
    }

    public QRYITNPORT getQryitnport() {
        return qryitnport;
    }

    public void setLport(String lport) {
        this.lport = lport;
    }

    public void setPipV4(String pipV4) {
        this.pipV4 = pipV4;
    }

    public void setPport(String pport) {
        this.pport = pport;
    }

    public void setLaddrn(String laddrn) {
        this.laddrn = laddrn;
    }

    public void setMeddn(String meddn) {
        this.meddn = meddn;
    }

    public void setChb(CHB chb) {
        this.chb = chb;
    }

    public void setRnit(String rnit) {
        this.rnit = rnit;
    }

    public void setCacPlcSetNameList(List<String> cacPlcSetNameList) {
        this.cacPlcSetNameList = cacPlcSetNameList;
    }

    public void setIpSetId(String ipSetId) {
        this.ipSetId = ipSetId;
    }

    public void setOpSetId(String opSetId) {
        this.opSetId = opSetId;
    }

    public void setQryitnport(QRYITNPORT qryitnport) {
        this.qryitnport = qryitnport;
    }

    @Override
    public String getCommand() {
        if (ipSetId != null && opSetId != null)
            return String.format("ADD ISIPTG:TGNAME=\"%s\", LINKINFO=%s, LADDRN=\"%s\", LPORT=%s,PIPTYPE=%s, PIPV4=\"%s\", " +
                    "PPORT=%s, MEDDN=\"%s\", CHB=%s,FROUTING=N, RNIT=\"%s\", CACPLCSETNAME1=\"%s\", IPSETID=\"%s\", OPSETID=" +
                    "\"%s\", SIGPLCNAME=\"DEFAULTIBCFSIGPLC\", BCPLCNAME=\"DEFAULTIBCFBCPLC\", TPNO=\"DefaultPolicy\", " +
                    "QRYITNPORT=%s, ADDTGRP=N, INNC=N, OUTNC=N, SETQOSTH=N;",
                    tgName, LINKINFO, laddrn, lport, PIPTYPE, pipV4, pport, meddn, chb, rnit, cacPlcSetNameList.get(0),
                    ipSetId, opSetId, qryitnport);
        else if (ipSetId != null)
            return String.format("ADD ISIPTG:TGNAME=\"%s\", LINKINFO=%s,LADDRN=\"%s\", LPORT=%s,PIPTYPE=%s, PIPV4=\"%s\", " +
                    "PPORT=%s, MEDDN=\"%s\", CHB=%s, FROUTING=N, RNIT=\"%s\", CACPLCSETNAME1=\"%s\", IPSETID=\"%s\", " +
                    "SIGPLCNAME=\"DEFAULTIBCFSIGPLC\", BCPLCNAME=\"DEFAULTIBCFBCPLC\", TPNO=\"DefaultPolicy\", " +
                    "QRYITNPORT=%s, ADDTGRP=N, INNC=N, OUTNC=N, SETQOSTH=N;",
                    tgName, LINKINFO, laddrn, lport, PIPTYPE, pipV4, pport, meddn, chb, rnit, cacPlcSetNameList.get(0),
                    ipSetId, qryitnport);
        else if (opSetId != null)
            return String.format("ADD ISIPTG:TGNAME=\"%s\", LINKINFO=%s, LADDRN=\"%s\", LPORT=%s, PIPTYPE=%s, PIPV4=\"%s\", " +
                    "PPORT=%s, MEDDN=\"%s\", CHB=%s, FROUTING=N, RNIT=\"%s\", CACPLCSETNAME1=\"%s\", OPSETID=\"%s\", " +
                    "SIGPLCNAME=\"DEFAULTIBCFSIGPLC\", BCPLCNAME=\"DEFAULTIBCFBCPLC\", TPNO=\"DefaultPolicy\", " +
                    "QRYITNPORT=%s, ADDTGRP=N, INNC=N, OUTNC=N, SETQOSTH=N;",
                    tgName, LINKINFO, laddrn, lport, PIPTYPE, pipV4, pport, meddn, chb, rnit, cacPlcSetNameList.get(0),
                    opSetId, qryitnport);
        else
            return String.format("ADD ISIPTG:TGNAME=\"%s\", LINKINFO=%s, LADDRN=\"%s\", LPORT=%s, PIPTYPE=%s, PIPV4=\"%s\", " +
                    "PPORT=%s, MEDDN=\"%s\", CHB=%s, FROUTING=N, RNIT=\"%s\", CACPLCSETNAME1=\"%s\", SIGPLCNAME=" +
                    "\"DEFAULTIBCFSIGPLC\", BCPLCNAME=\"DEFAULTIBCFBCPLC\", TPNO=\"DefaultPolicy\", QRYITNPORT=%s, ADDTGRP=" +
                    "N, INNC=N, OUTNC=N, SETQOSTH=N;",
                    tgName, LINKINFO, laddrn, lport, PIPTYPE, pipV4, pport, meddn, chb, rnit, cacPlcSetNameList.get(0),
                    qryitnport);
    }
}
