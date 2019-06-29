package ru.siberian.huawei.LogSaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.siberian.huawei.LogSaver.entity.Messages;
import ru.siberian.huawei.LogSaver.entity.MessagesRepository;
import ru.siberian.huawei.LogSaver.external.ExternalData;
import ru.siberian.huawei.LogSaver.managment.LogTimer;
import ru.siberian.huawei.LogSaver.managment.MyDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication

public class LogSaverApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LogSaverApplication.class);
		MessagesRepository repository = context.getBean(MessagesRepository.class);

		String stringDate = "2019-06-28 22:03:00+04:00";
		String stringDate1 = "2019-06-28 22:03:00";

        String[] timeString = stringDate.split("\\s+");

        Date dateStart = new MyDate().
                convertingStringToDate(timeString[0].trim() + " " + timeString[1].trim());
        LocalDateTime localDateTime = dateStart.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        System.out.println( new MyDate().convertLocalDateTimeToString(localDateTime.minusDays(1)));

		ExternalData externalData = new ExternalData();
		LogTimer timer = new LogTimer(externalData, repository);

//		repository.save(new Messages("SRT",  new Date(), "IM", "10.10.10.10", "LST TG:;"));
	}
}
