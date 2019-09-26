package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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
    private String tg1Name;

    @OneToOne(mappedBy = "tgName")
    public String getTg1Name(){
        return this.tg1Name;
    }

    @Transient
    @Override
    public String getCommand() {
        return String.format("ADD IOFC: OFCNAME=\"%s\", TG1NAME=\"%s\";",
                ofcName, tg1Name);
    }
}
