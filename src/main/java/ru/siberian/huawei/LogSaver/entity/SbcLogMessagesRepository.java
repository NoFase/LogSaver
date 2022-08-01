package ru.siberian.huawei.LogSaver.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SbcLogMessagesRepository extends CrudRepository<SbcLogMessages, Long> {

    @Query(value = "select m from SbcLogMessages m where m.cityName = :cityName")
    List<SbcLogMessages> findCity(@Param(value = "cityName") String cityName);


    @Query(value = "select m from SbcLogMessages m where m.cityName = :cityName and m.beginTime >= :beginTime and m.beginTime <= :endTime and m.description like concat('%', :description,'%')")
    List<SbcLogMessages> findAny(@Param("cityName") String cityName, Date beginTime, Date endTime, @Param("description") String description);

    @Query(value = "select m from SbcLogMessages m where m.cityName = :cityName and m.beginTime >= :beginTime and m.beginTime <= :endTime")
    List<SbcLogMessages> findWithoutCommand(@Param("cityName") String cityName, Date beginTime, Date endTime);

    @Query(value = "select m from SbcLogMessages m where m.cityName = :cityName")
    List<SbcLogMessages> findOnlyCity(@Param("cityName") String cityName);

    @Query(value = "select m from SbcLogMessages m where m.cityName = :cityName and m.description like concat('%', :description,'%')")
    List<SbcLogMessages> findCityCommand(@Param("cityName") String cityName, @Param("description") String description);
}
