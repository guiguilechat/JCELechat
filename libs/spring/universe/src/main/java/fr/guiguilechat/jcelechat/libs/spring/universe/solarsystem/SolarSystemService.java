package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.HashMap;
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

	@Override
	protected void updateResponseOk(SolarSystem data,
	    Requested<R_get_universe_systems_system_id> response) {
		super.updateResponseOk(data, response);
		R_get_universe_systems_system_id received = response.getOK();
		data.setConstellation(constellationService.createIfAbsent(received.constellation_id));
		if (received.planets != null && received.planets.length > 0) {

			Map<Integer, AsteroidBelt> abIdToEntity = new HashMap<>();
			List<Integer> systemABIds = Stream.of(received.planets)
			    .filter(p -> p.asteroid_belts != null && p.asteroid_belts.length > 0)
			    .flatMapToInt(p -> IntStream.of(p.asteroid_belts))
			    .boxed().toList();
			if (asteroidbelts && !systemABIds.isEmpty()) {
				abIdToEntity = asteroidBeltService.createIfAbsent(systemABIds);
			}

			Map<Integer, Moon> moonIdToEntity = new HashMap<>();
			List<Integer> systemMoonIds = Stream.of(received.planets)
			    .filter(p -> p.moons != null && p.moons.length > 0)
			    .flatMapToInt(p -> IntStream.of(p.moons))
			    .boxed().toList();
			if (moons && !systemMoonIds.isEmpty()) {
				moonIdToEntity = moonService.createIfAbsent(systemMoonIds);
			}

			if (planets) {
				Map<Integer, Planet> planetIdToEntity = planetService
				    .createIfAbsent(Stream.of(received.planets).mapToInt(p -> p.planet_id).boxed().toList());
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

			if (asteroidbelts) {
				asteroidBeltService.saveAll(abIdToEntity.values());
			}
			if (moons) {
				moonService.saveAll(moonIdToEntity.values());
			}
		}
		if (received.star_id != 0) {
			starService.createIfAbsent(received.star_id);
		}
		if (received.stargates != null && received.stargates.length > 0) {
			stargateService.createIfAbsent(IntStream.of(received.stargates).boxed().toList());
		}
		if (received.stations != null && received.stations.length > 0) {
			stationService.createIfAbsent(IntStream.of(received.stations).boxed().toList());
		}
	}

}
