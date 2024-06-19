package fr.guiguilechat.jcelechat.libs.spring.remotefetching;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.remotefetching")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.remotefetching")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.remotefetching")
@PropertySource("classpath:esiremote.properties")
public class RemoteFetchingConfiguration {

}
