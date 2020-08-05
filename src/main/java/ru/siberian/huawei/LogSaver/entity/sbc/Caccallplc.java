package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SBC.caccallplc")
@EnableJpaAuditing
public class Caccallplc {
    @Id
    @Length(min = 1, max = 31)
    @NotNull
    private String nameCacllplcname;
    private String msn;
    private String mcnt;
    private String mcr;

    @Transient
    private Cacplcset cacplcset;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "callplcname")
    public Cacplcset getCacplcset() {
        return cacplcset;
    }

    public Caccallplc(@Length(min = 1, max = 31) @NotNull String nameCacllplcname, String msn, String mcnt, String mcr) {
        this.nameCacllplcname = nameCacllplcname;
        this.msn = msn;
        this.mcnt = mcnt;
        this.mcr = mcr;
    }

    @Override
    public String toString() {
        return "Caccallplc" + '\t' +
                nameCacllplcname + '\'' +
                ", msn='" + msn + '\'' +
                ", mcnt='" + mcnt + '\'' +
                ", mcr='" + mcr + '\n';
    }
}
