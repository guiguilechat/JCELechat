package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporationDivisions;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeNpcDivision")
@Table(name = "sde_npc_division", indexes = {
//		@Index(columnList = "id,name")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Division {

	@Id
	private Integer id;
	@Builder.Default
	private boolean received = false;
	@Builder.Default
	private boolean removed = false;
	@CreatedDate
	private Instant created;

	private String displayName;
	private String internalName;
	private String leaderTypeName;
	private String name;

	public void update(EnpcCorporationDivisions entry) {
		received = true;
		removed = false;
		displayName = entry.displayName;
		internalName = entry.internalName;
		leaderTypeName = entry.leaderTypeName.getOrDefault("en", null);
		name = entry.enName();
	}

}
