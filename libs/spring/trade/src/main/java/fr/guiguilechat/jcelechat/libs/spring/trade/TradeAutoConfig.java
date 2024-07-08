package fr.guiguilechat.jcelechat.libs.spring.trade;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.trade")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.trade")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.trade")
public class TradeAutoConfig {
}
