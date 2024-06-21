package fr.guiguilechat.jcelechat.libs.spring.universe.station;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResource;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_universe_stations_station_id_services;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiUniverseStation")
@Table(name = "esi_universe_station", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "name"),
    @Index(columnList = "solar_system_id"),
    @Index(columnList = "type_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Station extends ARemoteResource<Integer, R_get_universe_stations_station_id> {

	/**
	 * The solar system this station is in
	 */
	@ManyToOne
	private SolarSystem solarSystem;

	/**
	 * type_id integer
	 */
	@ManyToOne
	private Type type;

	/**
	 * max_dockable_ship_volume number
	 */
	private float maxDockableShipVolume;

	/**
	 * name string
	 */
	private String name;

	/**
	 * office_rental_cost number
	 */
	private float officeRentalCost;

	/**
	 * ID of the corporation that controls this station
	 */
	private int ownerCorporationId;

	/**
	 * position object
	 */
	private double posX, posY, posZ;

	/**
	 * race_id integer
	 */
	private int raceId;

	/**
	 * reprocessing_efficiency number
	 */
	private float reprocessingEfficiency;

	/**
	 * reprocessing_stations_take number
	 */
	private float reprocessingStationsTake;

	/**
	 * services array
	 */
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<get_universe_stations_station_id_services> services = new HashSet<>();

	@Override
	public void update(R_get_universe_stations_station_id data) {
		setMaxDockableShipVolume(data.max_dockable_ship_volume);
		setName(data.name);
		setOfficeRentalCost(data.office_rental_cost);
		setOwnerCorporationId(data.owner);
		setPosX(data.position.x);
		setPosY(data.position.y);
		setPosZ(data.position.z);
		setRaceId(data.race_id);
		setReprocessingEfficiency(data.reprocessing_efficiency);
		setReprocessingStationsTake(data.reprocessing_stations_take);
		setServices(new HashSet<>(Set.of(data.services)));
	}

}
