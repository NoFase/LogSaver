package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SBC.caccallplc")
@EnableJpaAuditing
@Component
public class Caccallplc {
    @Id
    @Length(min = 1, max = 31)
    @NotNull
    private String nameCacllplcname;
    private String msn;
    private String mcnt;
    private String mcr;
}
