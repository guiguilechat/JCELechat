package fr.guiguilechat.jcelechat.libs.spring.remotefetching;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.templates")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.templates")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.templates")
public class RemoteFetchingConfiguration {

}
