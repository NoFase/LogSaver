package ru.siberian.huawei.LogSaver;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.siberian.huawei.LogSaver.entity.MessagesRepository;
import ru.siberian.huawei.LogSaver.entity.sbc.Iaddr;
import ru.siberian.huawei.LogSaver.entity.sbc.Irt;
import ru.siberian.huawei.LogSaver.entity.sbc.Isiptg;
import ru.siberian.huawei.LogSaver.external.ExternalData;
import ru.siberian.huawei.LogSaver.managment.LogTimer;

import java.util.Arrays;

@SpringBootApplication

public class LogSaverApplication {
	public static MessagesRepository repos;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LogSaverApplication.class);
		MessagesRepository repository = context.getBean(MessagesRepository.class);
		repos = repository;



//		new IconForTray();
//		System.out.println("<<<<<------REWRING BD FILE------>>>>>");
//		new TryIconMy();
		ExternalData externalData = new ExternalData();
		LogTimer timer = new LogTimer(externalData, repository);

//		repository.save(new Messages("SRT",  new Date(), "IM", "10.10.10.10", "LST TG:;"));
	}


}
