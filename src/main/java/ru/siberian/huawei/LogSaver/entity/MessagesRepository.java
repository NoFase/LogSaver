package ru.siberian.huawei.LogSaver.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepository extends CrudRepository<Messages, Long> {
    List<Messages> findByCityName(String cityName);
}
