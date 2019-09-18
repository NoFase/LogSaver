package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SBC.iofc")
public class Iofc implements Commandared{


//    Only for situation when we are create IOFC only for one ISIPTG
//    Office direction name
    @Id
    @Length(min = 1, max = 31)
    @NotNull
    private String ofcName;
//    Name of 1st trunk group
    @OneToOne(mappedBy = "tgName")
    private String tg1Name;

    @Override
    public String getCommand() {
        return String.format("ADD IOFC: OFCNAME=\"%s\", TG1NAME=\"%s\";",
                ofcName, tg1Name);
    }
}
