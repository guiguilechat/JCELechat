package fr.guiguilechat.jcelechat.libs.spring.sde.space.station;

import java.math.BigDecimal;
import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EnpcStations;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporation;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.generic.SdeInPlanetOrbit;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.moon.Moon;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.planet.Planet;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.star.Star;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.operation.StationOperation;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeSpaceStation")
@Table(name = "sde_space_station", indexes = {
		@Index(columnList = "solar_system_id"),
		@Index(columnList = "type_id"),
		@Index(columnList = "operation_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Station extends SdeInPlanetOrbit {

	/**
	 * the moon this orbits around when it exists. Otherwise it's around a planet or
	 * star
	 */
	@ManyToOne
	private Moon moon;
	@ManyToOne
	private StationOperation operation;
	@ManyToOne
	private NpcCorporation owner;
	/**
	 * the planet this orbits around when not around a moon or star.
	 */
	@ManyToOne
	private Planet planet;
	private BigDecimal reprocessingEfficiency;
	private int reprocessingHangarFlag;
	private BigDecimal reprocessingStationsTake;
	/**
	 * the star this orbits around when not around planet or moon
	 */
	@ManyToOne
	private Star star;
	private boolean useOperationName;

	public void update(EnpcStations source,
			Function<Integer, Type> types,
			Function<Integer, SolarSystem> solarSystems,
			Function<Integer, Moon> moons,
			Function<Integer, NpcCorporation> corporations,
			Function<Integer, Planet> planets,
			Function<Integer, Star> stars,
			Function<Integer, StationOperation> stationOperations) {
		super.update(source, types, solarSystems);
		setMoon(source.orbitsMoon() ? moons.apply(source.orbitID) : null);
		setOperation(stationOperations.apply(source.operationID));
		setOwner(corporations.apply(source.ownerID));
		setPlanet(source.orbitsPlanet() ? planets.apply(source.orbitID) : null);
		setReprocessingEfficiency(source.reprocessingEfficiency);
		setReprocessingHangarFlag(source.reprocessingHangarFlag);
		setReprocessingStationsTake(source.reprocessingStationsTake);
		setStar(source.orbitsStar() ? stars.apply(source.orbitID) : null);
		setUseOperationName(source.useOperationName);
	}

	public String getName() {
		return "station:" + getId();
	}

}
