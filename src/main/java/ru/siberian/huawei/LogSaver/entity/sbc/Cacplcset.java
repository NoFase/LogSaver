package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaAuditing
@Entity
@Table(name = "SBC.cacplcset")
public class Cacplcset {

    @Id
    @Length(min = 1, max = 31)
    @NotNull
    private String nameCacplcsetname;
    private String cacplcsetmode;
    private String callplcname;

    @OneToOne(mappedBy = "nameCacllplcname")
    public String getCallplcname() {
        return callplcname;
    }

    @Override
    public String toString() {
        return "Cacplcset" + '\t'
                + nameCacplcsetname + '\'' +
                ", cacplcsetmode='" + cacplcsetmode + '\'' +
                ", callplcname='" + callplcname + '\n';
    }
}
