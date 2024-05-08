package fr.guiguilechat.jcelechat.libs.spring.npc;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.npc")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.npc")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.npc")
public class NpcConfiguration {

}
