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
@Table(name = "SBC.vrf")
public class VRF {

    @Id
    @Length(min = 1, max = 31)
    @NotNull
    private String nameOfVrf;
    private String type;
    private String target;
    private String city;

    @Transient
    private Iaddr iaddr;

    @OneToOne(mappedBy = "vrfName", cascade = CascadeType.ALL)
    public Iaddr getIaddr() {
        return iaddr;
    }

    public VRF(String nameOfVrf) {
        this.nameOfVrf = nameOfVrf;
        autoSet();
    }

    private void autoSet(){
        String pattern = "[0-9]*";
        if (nameOfVrf.contains("sig") || nameOfVrf.contains("SIG")) setType("sig");
        else if (nameOfVrf.contains("rtp") || nameOfVrf.contains("RTP")) setType("rtp");
        else setType("none");

        if (nameOfVrf.contains("access") || nameOfVrf.contains("client")) setTarget("out_access");
        else if (nameOfVrf.contains("men")) setTarget("out_internet");
        else if (nameOfVrf.contains("MSS")) setTarget("core_MSS");
        else if (nameOfVrf.contains("sx3k") || nameOfVrf.contains("softx")) setTarget("core");
        else setTarget("none");

        String[] partOfName = nameOfVrf.split("_");
        for (String part: partOfName){
            if (!part.contains("sig") && !part.contains("SIG") && !part.contains("rtp") && !part.contains("RTP")
                    && !part.contains("access") && !part.contains("client") && !part.contains("men")
                    && !part.contains("internet") && !part.contains("sx3k") && !part.contains("softx") &&
            !Pattern.matches(pattern, part)) setCity(part);
        }
        if (city == null) setCity("none");
    }

    @Override
    public String toString() {
        return "VRF" + '\t' +
                nameOfVrf + '\'' +
                ", target='" + target + '\'' +
                ", city='" + city + '\n';
    }
}
