package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation;

import java.math.BigDecimal;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporations;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * basically the flat data of {@link EnpcCorporations}
 */
@Entity(name = "SdeNpcCorporation")
@Table(name = "sde_npc_corporation", indexes = {
		@Index(columnList = "faction_id")
})
@Getter
@Setter
@NoArgsConstructor
public class NpcCorporation extends SdeEntity<Integer> {

	private boolean deleted;
	private int factionId;
	private int friendId;
	private BigDecimal minSecurity;
	private int minimumJoinStanding;
	private String name;
	@ManyToOne
	private SolarSystem solarSystem;
	@ManyToOne
	private Station station;
	private BigDecimal taxRate;
	private String tickerName;
	private String uniqueName;

	public void update(EnpcCorporations source,
			Function<Integer, SolarSystem> solarSystems,
			Function<Integer, Station> stations) {

	}

}
