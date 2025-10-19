package fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.division;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporationDivisions;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.agent.NpcCharacter;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeNpcDivision")
@Table(name = "sde_npc_division", indexes = {
//		@Index(columnList = "id,name")
})
@Getter
@Setter
@NoArgsConstructor
public class Division extends SdeEntity<Integer> {

	@OneToMany(mappedBy = "division")
	private List<NpcCharacter> agents;
	private String displayName;
	private String internalName;
	private String leaderTypeName;
	private String name;

	public void update(EnpcCorporationDivisions entry) {
		receivedSource();
		displayName = entry.displayName;
		internalName = entry.internalName;
		leaderTypeName = entry.leaderTypeName.getOrDefault("en", null);
		name = entry.enName();
	}

}
