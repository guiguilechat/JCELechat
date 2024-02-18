package fr.guiguilechat.jcelechat.programs.spring.eveproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = {
		"fr.guiguilechat.jcelechat.libs.spring.market",
		"fr.guiguilechat.jcelechat.libs.spring.mer",
		"fr.guiguilechat.jcelechat.libs.spring.prices",
		"fr.guiguilechat.jcelechat.libs.spring.sde",
		"fr.guiguilechat.jcelechat.programs.spring.eveproxy"
})
@EnableJpaRepositories(basePackages = {
		"fr.guiguilechat.jcelechat.libs.spring.market",
		"fr.guiguilechat.jcelechat.libs.spring.mer",
		"fr.guiguilechat.jcelechat.libs.spring.prices",
		"fr.guiguilechat.jcelechat.libs.spring.sde",
		"fr.guiguilechat.jcelechat.programs.spring.eveproxy"
})
@EntityScan(basePackages = {
		"fr.guiguilechat.jcelechat.libs.spring.market",
		"fr.guiguilechat.jcelechat.libs.spring.mer",
		"fr.guiguilechat.jcelechat.libs.spring.prices",
		"fr.guiguilechat.jcelechat.libs.spring.sde",
		"fr.guiguilechat.jcelechat.programs.spring.eveproxy"
})
public class EveProxyApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EveProxyApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EveProxyApp.class);
	}

}