package fr.guiguilechat.jcelechat.programs.spring.eveproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import fr.guiguilechat.jcelechat.libs.spring.market.MarketReference;
import fr.guiguilechat.jcelechat.libs.spring.mer.MerReference;
import fr.guiguilechat.jcelechat.libs.spring.npc.NpcReference;
import fr.guiguilechat.jcelechat.libs.spring.prices.PricesReference;
import fr.guiguilechat.jcelechat.libs.spring.sde.SdeReference;

@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories
@EntityScan()
@Import({
		MarketReference.class,
		MerReference.class,
		NpcReference.class,
		PricesReference.class,
		SdeReference.class })
public class EveProxyApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EveProxyApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EveProxyApp.class);
	}

}