package ru.siberian.huawei.LogSaver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.siberian.huawei.LogSaver.entity.TDMIU.TableForSTM;

@Repository
public interface TableRepository extends CrudRepository<TableForSTM, String> {
}
