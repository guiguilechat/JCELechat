package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.time.Instant;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters.AgentData;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeNpcAgent")
@Table(name = "sde_npc_agent", indexes = {
// @Index(columnList = "activity_id,type_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agent {

	@Id
	private Integer id;
	@Builder.Default
	private boolean received = false;
	@Builder.Default
	private boolean removed = false;
	@CreatedDate
	private Instant created;

	@ManyToOne
	private Division division;
	@ManyToOne
	private AgentType agentType;
	private int corporationId;
	private boolean isLocator;
	private int level;
	private int locationId;
	private String name;

	public void update(EnpcCharacters entry,
			Map<Integer, AgentType> agentTypes,
			Map<Integer, Division> divisions) {
		received = true;
		removed = false;
		AgentData data = entry.agent;
		if (data != null) {
			agentType = agentTypes.computeIfAbsent(data.agentTypeID, i -> AgentType.builder().id(i).build());
			division = divisions.computeIfAbsent(data.divisionID, i -> Division.builder().id(i).build());
			isLocator = data.isLocator;
			level = data.level;
		} else {
			agentType = null;
			division = null;
			isLocator = false;
			level = 0;
		}
		corporationId = entry.corporationID;
		locationId = entry.locationID;
		name = entry.enName();
	}

}
