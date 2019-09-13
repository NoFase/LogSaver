package ru.siberian.huawei.LogSaver.entity.sbc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerConnectionOnSBC {
    private List<OneS> oneSList;
    private List<Iaddr> iaddr;
    private List<Isiptg> isiptgList;
    private List<Iofc> iofcList;
    private List<Irt> irtList;
    private String coreVrf;
    private String customerVrf;
    private String coreIpAddress;

    private Map<String, String> vrfMap = new HashMap<>();

    private String customerName;
    private List<String> ipAddresses;
    private String localPort;
    private String peerPort;
    private String typeOfService;
    private String city;
    private String reason;

    public CustomerConnectionOnSBC(String customerName, List<String> ipAddresses, String localPort, String peerPort, String typeOfService, String city, String reason) {
        this.customerName = customerName;
        this.ipAddresses = ipAddresses;
        this.localPort = localPort;
        this.peerPort = peerPort;
        this.typeOfService = typeOfService;
        this.city = city;
        this.reason = reason;
    }

    public CustomerConnectionOnSBC(List<OneS> oneSList, List<Iaddr> iaddr, List<Isiptg> isiptgList, List<Iofc> iofcList, List<Irt> irtList) {
        this.oneSList = oneSList;
        this.iaddr = iaddr;
        this.isiptgList = isiptgList;
        this.iofcList = iofcList;
        this.irtList = irtList;
    }


    public List<OneS> getOneSList() {
        return oneSList;
    }

    public List<Iaddr> getIaddr() {
        return iaddr;
    }

    public List<Isiptg> getIsiptgList() {
        return isiptgList;
    }

    public List<Iofc> getIofcList() {
        return iofcList;
    }

    public List<Irt> getIrtList() {
        return irtList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<String> getIpAddresses() {
        return ipAddresses;
    }

    public String getLocalPort() {
        return localPort;
    }

    public String getPeerPort() {
        return peerPort;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public String getCity() {
        return city;
    }

    public void creatingBatch(){
        oneSList = Arrays.asList(new OneS(reason));

        String nameForIADDR = "SIG_ADDR_" + city;
        if (typeOfService.equals("MK")  || typeOfService.equals("FMTN")) nameForIADDR += "-MSS-" + customerName;
        else nameForIADDR += "-" + customerName;
        iaddr.add(new Iaddr(nameForIADDR, coreIpAddress, coreVrf));


    }
}
