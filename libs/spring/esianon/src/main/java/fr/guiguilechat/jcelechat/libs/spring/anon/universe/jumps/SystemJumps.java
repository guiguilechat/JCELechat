package fr.guiguilechat.jcelechat.libs.spring.anon.universe.jumps;

import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.anon.universe.SystemPeriodEndKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniverseStatsJumps")
@Table(name = "esi_universe_statsjumps", indexes = {
		@Index(columnList = "solar_system_id, date"),
		@Index(columnList = "periodEnd")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(SystemPeriodEndKey.class)
public class SystemJumps {

	@Id
	private int solarSystemId;

	/**
	 * the last-modified of the fetched resource
	 */
	@Id
	private Instant periodEnd;

	/**
	 * deduced from last-modified and expire as lm-(expired-lm)
	 */
	private Instant periodStart;

	/**
	 * median of last-modified and previous date
	 */
	private Instant date;

	private int shipJumps;

}
