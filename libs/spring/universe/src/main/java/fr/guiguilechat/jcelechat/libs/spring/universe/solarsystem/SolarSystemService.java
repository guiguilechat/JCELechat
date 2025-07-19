package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.universe.asteroidbelt.AsteroidBelt;
import fr.guiguilechat.jcelechat.libs.spring.universe.asteroidbelt.AsteroidBeltService;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.universe.moon.Moon;
import fr.guiguilechat.jcelechat.libs.spring.universe.moon.MoonService;
import fr.guiguilechat.jcelechat.libs.spring.universe.planet.Planet;
import fr.guiguilechat.jcelechat.libs.spring.universe.planet.PlanetService;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors.SystemSelectorId;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.selectors.SystemSelectorName;
import fr.guiguilechat.jcelechat.libs.spring.universe.star.Star;
import fr.guiguilechat.jcelechat.libs.spring.universe.star.StarService;
import fr.guiguilechat.jcelechat.libs.spring.universe.stargate.Stargate;
import fr.guiguilechat.jcelechat.libs.spring.universe.stargate.StargateService;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.StationService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_universe_systems_system_id_planets;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.universe.solarsystem")
@Order(3)
public class SolarSystemService extends
    ARemoteEntityService<SolarSystem, Integer, R_get_universe_systems_system_id, SolarSystemRepository> {

	@Lazy
	private final AsteroidBeltService asteroidBeltService;

	@Lazy
	private final ConstellationService constellationService;

	@Lazy
	private final MoonService moonService;

	@Lazy
	private final PlanetService planetService;

	@Lazy
	private final StarService starService;

	@Lazy
	private final StargateService stargateService;

	@Lazy
	private final StationService stationService;

	@Override
	protected SolarSystem create(Integer entityId) {
		SolarSystem ret = new SolarSystem();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_systems_system_id> fetchData(Integer id,
	    Map<String, String> properties) {
		Requested<R_get_universe_systems_system_id> ret = ESIRawPublic.INSTANCE
		    .get_universe_systems(id, properties);
		return ret;
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_universe_systems(p).mapBody(List::of);
	}

	/**
	 * set to false to disable asteroid belt fetching from systems
	 */
	@Setter
	private boolean asteroidbelts = true;

	/**
	 * set to false to disable moons fetching from systems
	 */
	@Setter
	private boolean moons = true;

	/**
	 * set to false to disable planets fetching from systems
	 */
	@Setter
	private boolean planets = true;

	/**
	 * set to false to disable planets fetching from systems
	 */
	@Setter
	private boolean stars = true;

	/**
	 * set to false to disable planets fetching from systems
	 */
	@Setter
	private boolean stargates = true;

	/**
	 * set to false to disable planets fetching from systems
	 */
	@Setter
	private boolean stations = true;

	protected void updateResponseOk(SolarSystem data,
	    R_get_universe_systems_system_id received,
	    Map<Integer, AsteroidBelt> abIdToEntity,
	    Map<Integer, Constellation> idToConstellation,
	    Map<Integer, Moon> moonIdToEntity,
	    Map<Integer, Planet> planetIdToEntity) {
		data.setConstellation(idToConstellation.get(received.constellation_id));
		if (received.planets != null && received.planets.length > 0) {
			if (planets) {
				for (get_universe_systems_system_id_planets planetData : received.planets) {
					Planet planet = planetIdToEntity.get(planetData.planet_id);
					if (asteroidbelts && planetData.asteroid_belts != null && planetData.asteroid_belts.length > 0) {
						IntStream.of(planetData.asteroid_belts)
						    .mapToObj(abIdToEntity::get)
						    .forEach(asteroidBelt -> asteroidBelt.setPlanet(planet));
					}
					if (moons && planetData.moons != null && planetData.moons.length > 0) {
						IntStream.of(planetData.moons)
						    .mapToObj(moonIdToEntity::get)
						    .forEach(moon -> moon.setPlanet(planet));
					}
				}
			}
		}
	}

	@Override
	protected void updateResponseOk(Map<SolarSystem, R_get_universe_systems_system_id> responseOk) {
		super.updateResponseOk(responseOk);

		long start;

		Map<Integer, AsteroidBelt> idToAsteroidBelt = new HashMap<>();
		if (asteroidbelts) {
			start = System.currentTimeMillis();
			log.trace("creating asteroid belts");
			idToAsteroidBelt.putAll(asteroidBeltService
			    .createIfAbsent(responseOk.values().stream()
			        .filter(s -> s.planets != null).flatMap(s -> Stream.of(s.planets))
			        .filter(p -> p.asteroid_belts != null).flatMapToInt(p -> IntStream.of(p.asteroid_belts))
			        .boxed().toList()));
			log.trace(" created {} asteroid belts in {} s", idToAsteroidBelt.size(),
			    (System.currentTimeMillis() - start) / 1000);
		}

		start = System.currentTimeMillis();
		log.trace("creating constellations");
		Map<Integer, Constellation> idToConstellation = constellationService
		    .createIfAbsent(responseOk.values().stream()
		        .mapToInt(s -> s.constellation_id).distinct()
		        .boxed().toList());
		log.trace(" created {} constellations in {} s", idToConstellation.size(),
		    (System.currentTimeMillis() - start) / 1000);

		Map<Integer, Moon> idToMoon = new HashMap<>();
		if (moons) {
			start = System.currentTimeMillis();
			log.trace("creating moons");
			idToMoon.putAll(moonService
			    .createIfAbsent(responseOk.values().stream()
			        .filter(s -> s.planets != null).flatMap(s -> Stream.of(s.planets))
			        .filter(p -> p.moons != null).flatMapToInt(p -> IntStream.of(p.moons))
			        .boxed().toList()));
			log.trace(" created {} moons in {} s", idToMoon.size(), (System.currentTimeMillis() - start) / 1000);
		}

		Map<Integer, Planet> idToPlanet = new HashMap<>();
		if (planets) {
			start = System.currentTimeMillis();
			log.trace("creating planets");
			idToPlanet.putAll(planetService
			    .createIfAbsent(responseOk.values().stream()
			        .filter(s -> s.planets != null).flatMap(s -> Stream.of(s.planets))
			        .mapToInt(p -> p.planet_id)
			        .boxed().toList()));
			log.trace(" created {} planets in {} s", idToPlanet.size(), (System.currentTimeMillis() - start) / 1000);
		}

		Map<Integer, Star> idToStar = new HashMap<>();
		if (stars) {
			start = System.currentTimeMillis();
			log.trace("creating stars");
			idToStar.putAll(starService.createIfAbsent(responseOk.values().stream()
			    .filter(s -> s.star_id != 0).mapToInt(s -> s.star_id)
			    .boxed().toList()));
			log.trace(" created {} stars in {} s", idToStar.size(), (System.currentTimeMillis() - start) / 1000);
		}

		Map<Integer, Stargate> idToStargate = new HashMap<>();
		if (stargates) {
			start = System.currentTimeMillis();
			log.trace("creating stargates");
			idToStargate.putAll(stargateService.createIfAbsent(responseOk.values().stream()
			    .filter(s -> s.stargates != null).flatMapToInt(s -> IntStream.of(s.stargates))
			    .boxed().toList()));
			log.trace(" created {} stargates in {} s", idToStargate.size(), (System.currentTimeMillis() - start) / 1000);
		}

		Map<Integer, Station> idToStation = new HashMap<>();
		if (stations) {
			start = System.currentTimeMillis();
			log.trace("creating stations");
			idToStation.putAll(stationService.createIfAbsent(responseOk.values().stream()
			    .filter(s -> s.stations != null).flatMapToInt(s -> IntStream.of(s.stations))
			    .boxed().toList()));
			log.trace(" created {} stations in {} s", idToStation.size(), (System.currentTimeMillis() - start) / 1000);
		}

		responseOk.entrySet().stream()
		    .forEach(
		        e -> updateResponseOk(e.getKey(), e.getValue(), idToAsteroidBelt, idToConstellation, idToMoon, idToPlanet));

		if (asteroidbelts) {
			start = System.currentTimeMillis();
			log.trace("saving {} asteroid belts", idToAsteroidBelt.size());
			// save them because their solarSystem should be changed
			asteroidBeltService.saveAll(idToAsteroidBelt.values());
			log.trace(" saved {} asteroid belts in {} s", idToAsteroidBelt.size(),
			    (System.currentTimeMillis() - start) / 1000);
		}

		if (moons) {
			start = System.currentTimeMillis();
			log.trace("saving {} moons", idToMoon.size());
			moonService.saveAll(idToMoon.values());
			log.trace(" saved {} moons in {} s", idToMoon.size(),
			    (System.currentTimeMillis() - start) / 1000);
		}
	}

	//
	// usage
	//

	/**
	 * @param solar system
	 * @return all the solar systems of the destination of the stargates of the
	 *           given source system
	 */
	public List<SolarSystem> adjacent(SolarSystem source) {
		return repo().adjacent(source);
	}

	public List<Integer> adjacentIds(int solarSystemId) {
		return repo().adjacentIds(solarSystemId);
	}

	public Map<Integer, String> namesForIds(Iterable<Integer> systemIds) {
		return repo().findAllById(systemIds).stream().collect(Collectors.toMap(SolarSystem::getId, SolarSystem::name));
	}

	public List<Integer> selectNames(SystemSelectorName ssn, Iterable<String> names) {
		return ssn.apply(repo(), names);
	}

	public List<Integer> selectIds(SystemSelectorId ssi, Iterable<Integer> ids) {
		return ssi.apply(repo(), ids);
	}

	public List<Integer> listIds() {
		return repo().listIds();
	}

	public List<Integer> listIdsBySecurityBetween(float minSec, float maxSec) {
		return repo().listIdsBySecurityBetween(minSec, maxSec);
	}

	public List<Integer> listIdsByUniverse(String universe) {
		return repo().listIdsByUniverse(universe);
	}

	public List<Integer> listIdsByRegionId(int regionId) {
		return repo().listIdsByRegionId(regionId);
	}

	public List<Integer> listIdsByConstellationId(int constellationId) {
		return repo().listIdsByConstellationId(constellationId);
	}

}
