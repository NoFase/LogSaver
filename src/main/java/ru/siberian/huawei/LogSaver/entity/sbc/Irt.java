package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.siberian.huawei.LogSaver.repository.IofcRepository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SBC.irt")
public class Irt implements Commandared{

//    only for static routing between some ISIPTG
    private final String RTTYPE = "STATIC";

    @Id
    @Length(min = 1, max = 31)
    @NotNull
    private String rtName;
//    List of office direction names
    @Transient
    private List<String> ofcNames;

    @OneToMany
    private Set<Iofc> iofcSet;
//    Outbound trunk selection name
    private String snot;

    public Irt(@Length(min = 1, max = 31) @NotNull String rtName, List<String> ofcNames, String snot) {
        this.rtName = rtName;
        this.ofcNames = ofcNames;
        this.snot = snot;
    }

    @Override
    public String getCommand() {
        String outputCommand = String.format("ADD IRT: RTNAME=\"%s\", RTTYPE=%s, ", rtName, RTTYPE);
        if (ofcNames.size() != 0)
            for (int i = 0; i < ofcNames.size(); i++) {
                int j = i + 1;
                int priority = 100 + i * 10;
                outputCommand += "OFC" + j + "NAME=\"" + ofcNames.get(i) + "\", POFC" + j + "=" + priority + ", ";
            }
        outputCommand += "SNOT=\"" + snot + "\";";
        return outputCommand;
    }
}
