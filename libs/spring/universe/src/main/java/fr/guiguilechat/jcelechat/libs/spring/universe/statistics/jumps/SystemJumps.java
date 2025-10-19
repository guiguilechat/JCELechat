package fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps.SystemJumps.SystemJumpsFetch;
import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetch;
import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetch.BatchItem;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_jumps;
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

@Entity(name = "EsiUniverseStatsJumps")
@Table(name = "esi_universe_statsjumps", indexes = {
    @Index(columnList = "fetch_id"),
    @Index(columnList = "solar_system_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SystemJumps extends BatchItem<SystemJumpsFetch, R_get_universe_system_jumps> {

	@Entity(name = "EsiUniverseStatsJumpsFetch")
	@Table(name = "esi_universe_statsjumpsfetch", indexes = {
	    @Index(columnList = "status"),
	    @Index(columnList = "last_modified")
	})
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@NoArgsConstructor
	@Getter
	@Setter
	public static class SystemJumpsFetch extends BatchFetch<SystemJumps> {

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private SolarSystem solarSystem;

	private int shipJumps;

	@Override
	public SystemJumps update(R_get_universe_system_jumps fetched) {
		setShipJumps(fetched.ship_jumps);
		return this;
	}

}
