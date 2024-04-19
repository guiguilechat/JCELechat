package fr.guiguilechat.jcelechat.libs.spring.market;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.market")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.market")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.market")
public class MarketAutoConfig {
}
