package fr.guiguilechat.jcelechat.libs.spring.evehistory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market.MarketObservedRegionService;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import lombok.extern.slf4j.Slf4j;

@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
@Slf4j
public class EveHistoryApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EveHistoryApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(EveHistoryApp.class);
	}

	boolean addData = true;

	@Bean
	public CommandLineRunner initDB(MarketObservedRegionService mors) {
		return args -> {
			if (addData) {
				if (mors.observedRegions().isEmpty()) {
					for (Region r : Region.load().values().stream().filter(r -> r.isKS).toList()) {
						mors.observeRegion(r);
					}
// mors.observeRegions(
// 10000002, // The Forge
// 10000030, // Heimatar
// 10000032, // Sinq Laison
// 10000042, // Metropolis
// 10000043, // Domain
// 10000060// Delve
// );
				}
			}
			log.info("after startup, observed regions : " + mors.observedRegions());
		};
	}
}
