package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent;

import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCharacters.AgentData;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.division.Division;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.type.AgentType;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporation;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeNpcCharacter")
@Table(name = "sde_npc_character", indexes = {
		@Index(columnList = "name"),
		@Index(columnList = "division_id"),
		@Index(columnList = "agent_type_id"),
})
@Getter
@Setter
@NoArgsConstructor
public class NpcCharacter extends SdeEntity<Integer> {

	@ManyToOne
	private Division division;
	@ManyToOne
	private AgentType agentType;
	@ManyToOne
	private NpcCorporation corporation;
	private boolean isLocator;
	private int level;
	private int locationId;
	private String name;

	protected void update(
			EnpcCharacters source,
			Function<Integer, AgentType> agentTypes,
			Function<Integer, Division> divisions,
			Function<Integer, NpcCorporation> npcCorporations) {
		receivedSource();
		AgentData data = source.agent;
		if (data != null) {
			setAgentType(agentTypes.apply(data.agentTypeID));
			setDivision(divisions.apply(data.divisionID));
			setLocator(data.isLocator);
			setLevel(data.level);
		} else {
			setAgentType(null);
			setDivision(null);
			setLocator(false);
			setLevel(0);
		}
		setCorporation(npcCorporations.apply(source.corporationID));
		setLocationId(source.locationID);
		setName(source.enName());
	}

}
