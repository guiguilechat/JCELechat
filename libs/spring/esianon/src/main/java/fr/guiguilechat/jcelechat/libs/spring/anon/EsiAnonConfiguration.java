package fr.guiguilechat.jcelechat.libs.spring.anon;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.anon")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.anon")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.anon")
@PropertySource("classpath:esianon.properties")
public class EsiAnonConfiguration {

}