package fr.guiguilechat.jcelechat.libs.spring.npc.faction;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EsiNpcFaction")
@Table(name = "esi_npc_faction")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Faction {

	@Id
	private int factionId;

	/**
	 * corporation_id integer
	 */
	private int corporationId;

	/**
	 * description string
	 */
	@Column(columnDefinition = "TEXT")
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
	private int stationSystemCount;

	private boolean unicity;

	public void update(R_get_universe_factions data) {
		setCorporationId(data.corporation_id);
		setDescription(data.description);
		setMilitiaCorporationId(data.militia_corporation_id);
		setName(data.name);
		setSizeFactor(data.size_factor);
		setSolarSystemId(data.solar_system_id);
		setStationCount(data.station_count);
		setStationSystemCount(data.station_system_count);
		setUnicity(data.is_unique);
	}

}
