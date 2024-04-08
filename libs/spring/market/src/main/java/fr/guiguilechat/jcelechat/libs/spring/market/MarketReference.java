package fr.guiguilechat.jcelechat.libs.spring.market;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.market")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.market")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.market")
public class MarketReference {
}
