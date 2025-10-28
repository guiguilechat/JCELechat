package fr.guiguilechat.jcelechat.libs.spring.affiliations.faction;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.FetchedEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiAffiliationsFactionInfo")
@Table(name = "esi_affiliations_factioninfo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FactionInfo extends FetchedEntity<Integer> {

	@ManyToOne
	private CorporationInfo corporation;

	/**
	 * description string
	 */
	@Lob
	private String description;

	/**
	 * militia_corporation_id integer
	 */
	private int militiaCorporationId;

	/**
	 * name string
	 */
	private String name;

	/**
	 * size_factor number
	 */
	private float sizeFactor;

	/**
	 * solar_system_id integer
	 */
	private int solarSystemId;

	/**
	 * station_count integer
	 */
	private int stationCount;

	/**
	 * station_system_count integer
	 */
	private int stationSystemSount;

	/**
	 * is_unique boolean
	 */
	private boolean uniq;

	public void update(R_get_universe_factions data) {
		setDescription(data.description);
		setMilitiaCorporationId(data.militia_corporation_id);
		setName(data.name);
		setSizeFactor(data.size_factor);
		setSolarSystemId(data.solar_system_id);
		setStationCount(data.station_count);
		setStationSystemSount(data.station_system_count);
		setUniq(data.is_unique);
	}

}
