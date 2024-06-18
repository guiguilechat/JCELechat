package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.libs.spring.universe.asteroidbelt.AsteroidBelt;
import fr.guiguilechat.jcelechat.libs.spring.universe.asteroidbelt.AsteroidBeltService;
import fr.guiguilechat.jcelechat.libs.spring.universe.constellation.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.universe.moon.Moon;
import fr.guiguilechat.jcelechat.libs.spring.universe.moon.MoonService;
import fr.guiguilechat.jcelechat.libs.spring.universe.planet.Planet;
import fr.guiguilechat.jcelechat.libs.spring.universe.planet.PlanetService;
import fr.guiguilechat.jcelechat.libs.spring.universe.star.StarService;
import fr.guiguilechat.jcelechat.libs.spring.universe.stargate.StargateService;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.StationService;
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
    ARemoteFetchedResourceService<SolarSystem, Integer, R_get_universe_systems_system_id, SolarSystemRepository> {

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
	    Map<Integer, Moon> moonIdToEntity,
	    Map<Integer, Planet> planetIdToEntity) {
		data.setConstellation(constellationService.createIfAbsent(received.constellation_id));
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
		log.trace("creating asteroid belts");
		Map<Integer, AsteroidBelt> idToAsteroidBelt = asteroidbelts ? asteroidBeltService
		    .createIfAbsent(responseOk.values().stream()
		        .filter(s -> s.planets != null).flatMap(s -> Stream.of(s.planets))
		        .filter(p -> p.asteroid_belts != null).flatMapToInt(p -> IntStream.of(p.asteroid_belts))
		        .boxed().toList())
		    : Map.of();

		log.trace("creating moons");
		Map<Integer, Moon> idToMoon = moons ? moonService
		    .createIfAbsent(responseOk.values().stream()
		        .filter(s -> s.planets != null).flatMap(s -> Stream.of(s.planets))
		        .filter(p -> p.moons != null).flatMapToInt(p -> IntStream.of(p.moons))
		        .boxed().toList())
		    : Map.of();

		log.trace("creating planets");
		Map<Integer, Planet> idToPlanet = planets ? planetService
		    .createIfAbsent(responseOk.values().stream()
		        .filter(s -> s.planets != null).flatMap(s -> Stream.of(s.planets))
		        .mapToInt(p -> p.planet_id)
		        .boxed().toList())
		    : Map.of();

		log.trace("creating stars");
		if (stars) {
			starService.createIfAbsent(responseOk.values().stream()
			        .filter(s -> s.star_id != 0).mapToInt(s -> s.star_id)
			    .boxed().toList());
		}

		log.trace("creating stargates");
		if (stargates) {
			stargateService.createIfAbsent(responseOk.values().stream()
			        .filter(s -> s.stargates != null).flatMapToInt(s -> IntStream.of(s.stargates))
			    .boxed().toList());
		}

		log.trace("creating stations");
		if (stations) {
			stationService.createIfAbsent(responseOk.values().stream()
			        .filter(s -> s.stations != null).flatMapToInt(s -> IntStream.of(s.stations))
			    .boxed().toList());
		}

		log.trace("creating systems");
		responseOk.entrySet().stream()
		    .forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToAsteroidBelt, idToMoon, idToPlanet));

		log.trace("saving asteroid belts");
		// save them because their solarSystem should be changed
		asteroidBeltService.saveAll(idToAsteroidBelt.values());
		log.trace("saving moons");
		moonService.saveAll(idToMoon.values());
	}

	/**
	 * @param solar system
	 * @return all the solar systems of the destination of the stargates of the
	 *           given source system
	 */
	public List<SolarSystem> adjacent(SolarSystem source) {
		return repo().adjacent(source);
	}

}
