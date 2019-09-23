package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@EnableJpaAuditing
//@Component
@Entity
@Table(name = "SBC.cacplcset")
public class Cacplcset {

    @Id
    @Length(min = 1, max = 31)
    @NotNull
    private String nameCacplcsetname;
    private String cacplcsetmode;
    private String callplcname;
}
