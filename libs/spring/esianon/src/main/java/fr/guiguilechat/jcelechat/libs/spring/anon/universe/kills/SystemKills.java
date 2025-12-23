package fr.guiguilechat.jcelechat.libs.spring.anon.universe.kills;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.anon.universe.kills.SystemKills.SystemKillsFetch;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetch;
import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetch.BatchItem;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_kills;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniverseStatsKills")
@Table(name = "esi_universe_statskills", indexes = {
    @Index(columnList = "fetch_id"),
    @Index(columnList = "solar_system_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SystemKills extends BatchItem<SystemKillsFetch, R_get_universe_system_kills> {

	@Entity(name = "EsiUniverseStatsKillsFetch")
	@Table(name = "esi_universe_statskillsfetch", indexes = {
	    @Index(columnList = "status"),
	    @Index(columnList = "last_modified")
	})
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@NoArgsConstructor
	@Getter
	@Setter
	public static class SystemKillsFetch extends BatchFetch<SystemKills> {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private SolarSystem solarSystem;

	/**
	 * Number of NPC ships killed in this system
	 */
	private int npcKills;

	/**
	 * Number of pods killed in this system
	 */
	private int podKills;

	/**
	 * Number of player ships killed in this system
	 */
	private int shipKills;

	@Override
	public SystemKills update(R_get_universe_system_kills fetched) {
		setNpcKills(fetched.npc_kills);
		setPodKills(fetched.pod_kills);
		setShipKills(fetched.ship_kills);
		return this;
	}

}
