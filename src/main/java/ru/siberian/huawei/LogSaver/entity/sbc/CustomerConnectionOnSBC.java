package ru.siberian.huawei.LogSaver.entity.sbc;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Entity
//@Table(name = "/SBC/customers")
public class CustomerConnectionOnSBC {

//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

//    @Column(name="1c")
//    @OneToMany()
    private List<OneS> oneSList;

//    @OneToMany(cascade = CascadeType.ALL)
    private List<Iaddr> iaddr;

//    @OneToMany(cascade = CascadeType.ALL)
    private List<Isiptg> isiptgList;

//    @OneToMany(cascade = CascadeType.ALL)
    private List<Iofc> iofcList;

//    @OneToMany(cascade = CascadeType.ALL)
    private List<Irt> irtList;

    private String coreVrf;
    private String customerVrf;
    private String coreIpAddress;

    private Map<String, String> vrfMap = new HashMap<>();

    private String customerName;
//    @Transient
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

    public String toString(){
        String string = customerName + "\n";
        string = "\t1c:\n";
        for (OneS oneS: oneSList){
            string += "\t\t" + oneS.getNumber() + "\n";
        }
        string += "\tiSIPTG:\n";
        for (Isiptg isiptg: isiptgList){
            string += "\t\tiSIPTG name: " + isiptg.getTgName() + "\n";
            string += "\t\t\tLocal IpName: " + isiptg.getLaddrn().getSignalingAddressName() + "\tIp: " + isiptg.getLaddrn().getIpAddressV4() +  "\n";
            string += "\t\t\tRNIT: " + isiptg.getRnit() + "\n";
            string += "\t\t\tRemoute Ip: " + isiptg.getPipV4() + "\n";
        }
        string += "\tIOFC:\n";
        for (Iofc iofc: iofcList){
            string += "\t\t" + iofc.getTg1Name() + "\n";
        }
        string += "\tIRT:\n";
        for (Irt irt: irtList){
            string += "\t\tiRT name: " + irt.getRtName() + "\n";
            string += "\t\t\tiOFCs:\n";
            for (String s: irt.getOfcNames()){
                string += "\t\t\t\t" + s + "\n";
            }
            string += "\t\t\tSNOT:" + irt.getSnot() + "\n";
        }
        return string;
    }

    public void creatingBatch(){
        oneSList = Arrays.asList(new OneS(reason));

        String nameForIADDR = "SIG_ADDR_" + city;
        if (typeOfService.equals("MK")  || typeOfService.equals("FMTN")) nameForIADDR += "-MSS-" + customerName;
        else nameForIADDR += "-" + customerName;
        iaddr.add(new Iaddr(nameForIADDR, coreIpAddress, coreVrf));


    }
}
