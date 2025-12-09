package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation;

import java.math.BigDecimal;
import java.util.function.Function;

import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcCorporations;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.Station;
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

	@ColumnDefault("false")
	private boolean deleted;
	@ColumnDefault("0")
	private int factionId;
	@ColumnDefault("0")
	private int friendId;
	@ColumnDefault("0")
	private BigDecimal minSecurity;
	@ColumnDefault("0")
	private int minimumJoinStanding;
	private String name;
	@ManyToOne
	private SolarSystem solarSystem;
	@ManyToOne
	private Station station;
	@ColumnDefault("0")
	private BigDecimal taxRate;
	private String tickerName;
	@ColumnDefault("false")
	private boolean uniqueName;

	public String nameOrId() {
		return name != null ? name : "npccorporation:" + getId();
	}

	public void update(EnpcCorporations source,
			Function<Integer, SolarSystem> solarSystems,
			Function<Integer, Station> stations) {
		receivedSource();
		setDeleted(source.deleted);
		setFactionId(source.factionID);
		setFriendId(source.friendID);
		setMinSecurity(source.minSecurity);
		setMinimumJoinStanding(source.minimumJoinStanding);
		setName(source.enName());
		setTaxRate(source.taxRate);
		setTickerName(source.tickerName);
		setUniqueName(source.uniqueName);
	}

}
