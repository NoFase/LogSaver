package ru.siberian.huawei.LogSaver.repository;

import org.springframework.data.repository.CrudRepository;
import ru.siberian.huawei.LogSaver.entity.sbc.Iofc;

public interface IofcRepository extends CrudRepository<Iofc, String> {
}
