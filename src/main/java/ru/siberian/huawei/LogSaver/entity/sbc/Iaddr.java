package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SBC.iaddr")
public class Iaddr implements Commandared{

//    Only for situation when we are create IADDR for internal IPV4 and for HRU module 151
//    HRU module ID
    @Transient
    private final String HRUMID = "151";
//    Domain Type
    @Transient
    private final String DMT = "INTERNAL";
    @Transient
    private final String IPADDRESSTYPE = "IPV4";
    @Transient
    private final String VRFFLAG = "Y";


    @Id
    @Length(min = 1, max = 31)
    @NotNull
    private String signalingAddressName;

    private String ipAddressV4;

    private String vrfName;

    @OneToOne(mappedBy = "nameOfVrf")
    public String getVrfName(){
        return this.vrfName;
    }

    @Transient
    private Iofc iofc;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tg1Name")
    public Iofc getIofc(){
        return iofc;
    }

    @Transient
    private final String regexIp = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    public Iaddr(String signalingAddressName, String ipAddressV4, String vrfName) {
        this.signalingAddressName = signalingAddressName;
        if (Pattern.matches(regexIp, ipAddressV4)) this.ipAddressV4 = ipAddressV4;
        else System.out.println("ERROR ip address - Iaddr");
        this.vrfName = vrfName;
    }

    @Transient
    @Override
    public String getCommand() {
        return String.format("ADD IADDR: ADDRNAME=\"%s\", HRUMID=%s, DMT=%s, IPTYPE=%s, " +
                "IPV4=\"%s\", VRFFLAG=%s, VRFNAME=\"%s\";",
                signalingAddressName, HRUMID, DMT, IPADDRESSTYPE, ipAddressV4, VRFFLAG, vrfName);
    }
}
