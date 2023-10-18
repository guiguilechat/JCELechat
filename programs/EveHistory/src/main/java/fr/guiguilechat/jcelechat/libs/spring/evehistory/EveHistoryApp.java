package fr.guiguilechat.jcelechat.libs.spring.evehistory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.MarketFetchResultService;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class EveHistoryApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EveHistoryApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EveHistoryApp.class);
	}

	boolean addData = true;

	@Bean
	public CommandLineRunner initDB(MarketFetchResultService mfrs) {
		return args -> {
			if (addData) {
				mfrs.observeRegions(
						10000002, // The Forge
						10000030, // Heimatar
						10000032, // Sinq Laison
						10000042, // Metropolis
						10000043, // Domain
						10000060// Delve
						);
				log.info("startup added regions : " + mfrs.observedRegions());
			}
		};
	}
}
