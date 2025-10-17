package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporationDivisions;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Division extends SdeEntity<Integer> {

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
