package ru.siberian.huawei.LogSaver.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MessagesRepository extends CrudRepository<Messages, Long> {
    List<Messages> findByCityName(String cityName);

    @Query(value = "select m from Messages m where m.cityName = :cityName and m.date >= :dateStart and m.date <= :dateEnd and m.command like concat('%', :command,'%')")
    List<Messages> findAny(@Param("cityName") String cityName, Date dateStart, Date dateEnd, @Param("command") String command);

    @Query(value = "select m from Messages m where m.cityName = :cityName and m.date >= :dateStart and m.date <= :dateEnd")
    List<Messages> findWithoutCommand(@Param("cityName") String cityName, Date dateStart, Date dateEnd);

    @Query(value = "select m from Messages m where m.cityName = :cityName")
    List<Messages> findOnlyCity(@Param("cityName") String cityName);
}
