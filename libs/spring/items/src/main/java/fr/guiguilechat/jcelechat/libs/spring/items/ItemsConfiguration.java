package fr.guiguilechat.jcelechat.libs.spring.items;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.items")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.items")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.items")
public class ItemsConfiguration {

}