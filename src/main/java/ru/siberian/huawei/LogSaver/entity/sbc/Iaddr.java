package ru.siberian.huawei.LogSaver.entity.sbc;

import java.util.regex.Pattern;

public class Iaddr implements Commandared{
//    Only for situation when we are create IADDR for internal IPV4 and for HRU module 151
//    HRU module ID
    private final String HRUMID = "151";
//    Domain Type
    private final String DMT = "INTERNAL";
    private final String IPADDRESSTYPE = "IPV4";
    private final String VRFFLAG = "Y";

    private String signalingAddressName;
    private String ipAddressV4;
    private String vrfName;

    private String regexIp = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    public Iaddr(String signalingAddressName, String ipAddressV4, String vrfName) {
        if (signalingAddressName.length() < 32) this.signalingAddressName = signalingAddressName;
//        !!!!!!!!!!!!! NEED OUTPUT ALARM ABOUT NOT CORRECT DATA
        else System.out.println("ERROR name IADDR");
        if (Pattern.matches(regexIp, ipAddressV4)) this.ipAddressV4 = ipAddressV4;
        else System.out.println("ERROR ip address");
        this.vrfName = vrfName;
    }

    public String getSignalingAddressName() {
        return signalingAddressName;
    }

    public String getIpAddressV4() {
        return ipAddressV4;
    }

    public String getVrfName() {
        return vrfName;
    }

    @Override
    public String getCommand() {
        return String.format("ADD IADDR: ADDRNAME=\"%s\", HRUMID=%s, DMT=%s, IPTYPE=%s, " +
                "IPV4=\"%s\", VRFFLAG=%s, VRFNAME=\"%s\";",
                signalingAddressName, HRUMID, DMT, IPADDRESSTYPE, ipAddressV4, VRFFLAG, vrfName);
    }
}
