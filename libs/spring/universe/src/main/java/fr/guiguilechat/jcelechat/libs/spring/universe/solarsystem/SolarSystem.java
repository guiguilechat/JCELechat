package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import fr.guiguilechat.jcelechat.libs.spring.universe.asteroidbelt.AsteroidBelt;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.universe.moon.Moon;
import fr.guiguilechat.jcelechat.libs.spring.universe.planet.Planet;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.universe.star.Star;
import fr.guiguilechat.jcelechat.libs.spring.universe.stargate.Stargate;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.Station;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniverseSolarSystem")
@Table(name = "sde_universe_solarsystem", indexes = {
//		@Index(columnList = "fetch_active,expires"),
		@Index(columnList = "constellation_id"),
		@Index(columnList = "name") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolarSystem extends SdeEntity<Integer> {

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
	private BigDecimal posX, posY, posZ;
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

	public Space getSpace() {
		String regionUniverse = null;
		Constellation c = getConstellation();
		if (c != null) {
			Region r = c.getRegion();
			if (r != null) {
				regionUniverse = r.getUniverse();
			}
		}
		return Space.of(getSecurityStatus().floatValue(), regionUniverse);
	}

}
