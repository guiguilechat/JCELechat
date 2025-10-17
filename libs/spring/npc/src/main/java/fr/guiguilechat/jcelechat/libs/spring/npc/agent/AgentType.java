package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EagentTypes;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeNpcAgentType")
@Table(name = "sde_npc_agenttype", indexes = {
		@Index(columnList = "id,name")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentType extends SdeEntity<Integer> {

	private String name;

	public void update(EagentTypes entry) {
		receivedSource();
		name = entry.name;
	}

}
