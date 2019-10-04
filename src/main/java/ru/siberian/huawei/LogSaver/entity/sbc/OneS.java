package ru.siberian.huawei.LogSaver.entity.sbc;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SBC.1c")
public class OneS {
    @Id
    @NonNull
    private String number;

    @Override
    public String toString() {
        return "1c:" + '\t' +
        number + '\n';
    }
}
