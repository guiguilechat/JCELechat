package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.type;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EagentTypes;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.NpcCharacter;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class AgentType extends SdeEntity<Integer> {

	@OneToMany(mappedBy = "agentType")
	private List<NpcCharacter> agents;
	private String name;

	public void update(EagentTypes entry) {
		receivedSource();
		name = entry.name;
	}

}
