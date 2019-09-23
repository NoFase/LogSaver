package ru.siberian.huawei.LogSaver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.siberian.huawei.LogSaver.entity.sbc.Iofc;

@Repository
public interface IofcRepository extends CrudRepository<Iofc, String> {
}
