package fr.guiguilechat.jcelechat.programs.spring.noscrapeve;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class NoScrapEveApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NoScrapEveApp.class, args);
		log.debug("started context" + context);
	}

}