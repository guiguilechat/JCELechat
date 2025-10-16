package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EagentTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeNpcAgentType")
@Table(name = "sde_npc_agenttype", indexes = {
		@Index(columnList = "id,name")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AgentType {

	@Id
	private Integer id;
	@Builder.Default
	private boolean received = false;
	@Builder.Default
	private boolean removed = false;
	@CreatedDate
	private Instant created;

	private String name;

	public void update(EagentTypes entry) {
		received = true;
		removed = false;
		name = entry.name;
	}

}
