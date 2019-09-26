package ru.siberian.huawei.LogSaver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.siberian.huawei.LogSaver.entity.sbc.Irt;

@Repository
public interface IrtRepository extends CrudRepository<Irt, String> {
}
