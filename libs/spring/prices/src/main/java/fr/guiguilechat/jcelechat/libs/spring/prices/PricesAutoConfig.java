package fr.guiguilechat.jcelechat.libs.spring.prices;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.prices")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.prices")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.prices")
public class PricesAutoConfig {
}
