package ru.siberian.huawei.LogSaver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.siberian.huawei.LogSaver.entity.sbc.OneS;

@Repository
public interface OneSRepository extends CrudRepository<OneS, String> {
}
