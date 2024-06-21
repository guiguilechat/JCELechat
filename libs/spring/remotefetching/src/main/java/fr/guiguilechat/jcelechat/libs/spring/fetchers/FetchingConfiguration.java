package fr.guiguilechat.jcelechat.libs.spring.fetchers;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.fetchers")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.fetchers")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.fetchers")
@PropertySource("classpath:esifetchers.properties")
public class FetchingConfiguration {

}
