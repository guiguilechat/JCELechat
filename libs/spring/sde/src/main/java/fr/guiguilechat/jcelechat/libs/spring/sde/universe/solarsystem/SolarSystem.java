package fr.guiguilechat.jcelechat.libs.spring.sde.universe.solarsystem;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapSolarSystems;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.asteroidbelt.AsteroidBelt;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.generic.SdeInSpace;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.moon.Moon;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.planet.Planet;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.star.Star;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.stargate.Stargate;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.station.Station;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniverseSolarSystem")
@Table(name = "sde_universe_solarsystem", indexes = {
		@Index(columnList = "constellation_id"),
		@Index(columnList = "name") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class SolarSystem extends SdeInSpace {

	@OneToMany(mappedBy = "solarSystem")
	private List<AsteroidBelt> asteroidBelts;

	@ManyToOne
	private Constellation constellation;

	@OneToMany(mappedBy = "solarSystem")
	private List<Moon> moons;

	@OneToMany(mappedBy = "solarSystem")
	private List<Planet> planets;

	@OneToOne(mappedBy = "solarSystem")
	private Star star;

	@OneToMany(mappedBy = "solarSystem")
	private List<Stargate> stargates;

	@OneToMany(mappedBy = "solarSystem")
	private List<Station> stations;

	private String name;
	private String securityClass;
	private BigDecimal securityStatus;
	private int wormholeClassID;

	public String name() {
		if (name != null) {
			return name;
		}
		return "solsys:" + getId();
	}

	@Override
	public String toString() {
		return name == null ? "solsys:" + getId() : name + "(" + getId() + ")";
	}

	public void update(EmapSolarSystems source, Function<Integer, Constellation> constellations) {
		super.update(source);
		setConstellation(constellations.apply(source.constellationID));
		setName(source.enName());
		setSecurityClass(source.securityClass);
		setSecurityStatus(source.securityStatus);
		setWormholeClassID(source.wormholeClassID);
	}

}
