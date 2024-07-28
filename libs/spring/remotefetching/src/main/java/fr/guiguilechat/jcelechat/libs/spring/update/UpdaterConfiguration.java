package fr.guiguilechat.jcelechat.libs.spring.update;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.update")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.update")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.update")
@PropertySource("classpath:esifetchers.properties")
public class UpdaterConfiguration {

}
