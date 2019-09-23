package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SBC.isiptg")
public class Isiptg implements Commandared{

    private final String LINKINFO = "UDP";
    private final String PIPTYPE = "IPV4";

//    data to be specified
    @Id
    @Length(min = 1, max = 31)
    @NotNull
//    @OneToOne(mappedBy = "tg1Name", cascade = CascadeType.ALL)
    private String tgName;
//    Local port number
    private String lport;
//    Peer IPv4 address
    private String pipV4;
//    Peer port number
    private String pport;

//    data to be selected
//    Local address name
//    private String laddrn;
    @Embedded
    private Iaddr laddrn;
    private String meddn;
//    Heartbeat detection
    private String chb;
//    Inbound trunk route name
    private String rnit;
//    CAC policy set name (max 3)
    @Embedded
    private List<String> cacPlcSetNameList;
//    InboundHMR policy set ID
    private String ipSetId;
//    Outbound HMR policy set ID
    private String opSetId;
//    List ignored ports for inbound trunk (Y/N)
    private String qryitnport;
    @Transient
    private String signalingAddressName;

    @Transient
    private final String regexIp = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    public Isiptg(String tgName, String lport, String pipV4, String pport) {
        this.tgName = tgName;
        this.lport = lport;
        if (Pattern.matches(regexIp, pipV4)) this.pipV4 = pipV4;
        else System.out.println("ERROR ip address -Isiptg");
        this.pport = pport;
    }

    @Transient
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
