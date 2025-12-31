package fr.guiguilechat.jcelechat.libs.exports.locations.translate;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.libs.exports.locations.ALocation;
import fr.guiguilechat.jcelechat.libs.exports.locations.Constellation;
import fr.guiguilechat.jcelechat.libs.exports.locations.Region;
import fr.guiguilechat.jcelechat.libs.exports.locations.SolarSystem;
import fr.guiguilechat.jcelechat.libs.exports.locations.Station;

/**
 * translate the locations from the sde in specific collection.<br />
 * Don't call this class outside of the SDE package, as it relies on
 * SDE-compiler which is optional.<br />
 * If I could remove that class from package, that's would be better.
 */
public class LocationsTranslater {

	public static final Logger logger = LoggerFactory.getLogger(LocationsTranslater.class);

	public enum REGION_TYPE {
		ABYSSAL, KS, JOVIAN, PENALTY, POCHVEN, STARTER, WORMHOLE,
	}

	static void delDir(File delete) {
		if (delete.exists()) {
			if (delete.isDirectory()) {
				for (File child : delete.listFiles()) {
					delDir(child);
				}
			}
			delete.delete();
		}
	}

	/**
	 * @param args
	 *             should be [database destination root], typically
	 *             src/generated/resources/
	 */
	public static void main(String[] args) {

		int parrallelism = Runtime.getRuntime().availableProcessors() * 100;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length == 0 ? "src/generated/resources/" : args[0]);
		delDir(folderOut);
		folderOut.mkdirs();

		LocationTranslation loaded = loadSDE();

		// sort

		LinkedHashMap<String, Region> regions = new LinkedHashMap<>();
		loaded.regions().stream().sorted(Comparator.comparing(ALocation::name))
				.forEach(o -> regions.put(o.name(), o));

		LinkedHashMap<String, Constellation> constellations = new LinkedHashMap<>();
		loaded.constellations().stream().sorted(Comparator.comparing(ALocation::name))
				.forEach(o -> constellations.put(o.name(), o));

		LinkedHashMap<String, SolarSystem> systems = new LinkedHashMap<>();
		loaded.systems().stream().sorted(Comparator.comparing(ALocation::name))
				.forEach(o -> systems.put(o.name(), o));

		LinkedHashMap<String, Station> stations = new LinkedHashMap<>();
		loaded.stations().stream().sorted(Comparator.comparing(Station::name))
				.forEach(o -> stations.put(o.name(), o));

		// save

		Region.export(regions, folderOut);
		Constellation.export(constellations, folderOut);
		SolarSystem.export(systems, folderOut);
		Station.export(stations, folderOut);

		logger.info("exported locations in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");

	}

	public static record LocationTranslation(
			List<Region> regions,
			List<Constellation> constellations,
			List<SolarSystem> systems,
			List<Station> stations) {
	}

	public static LocationTranslation loadSDE() {
		List<Region> regions = new ArrayList<>();
		List<Constellation> constellations = new ArrayList<>();
		List<SolarSystem> systems = new ArrayList<>();
		List<Station> stations = new ArrayList<>();
		fr.guiguilechat.jcelechat.libs.sde.model.locations.Region.CACHE.all().forEach(sourceRegion -> {
			Region r = new Region();
			r.centerX = sourceRegion.position().x;
			r.centerY = sourceRegion.position().y;
			r.centerZ = sourceRegion.position().z;
			r.adjacentConstellations = sourceRegion.adjacentConstellations().stream()
					.map(fr.guiguilechat.jcelechat.libs.sde.model.locations.Constellation::enName)
					.sorted()
					.toList();
			r.adjacentRegions = sourceRegion.adjacentRegions().stream()
					.map(fr.guiguilechat.jcelechat.libs.sde.model.locations.Region::enName)
					.sorted()
					.toList();
			r.adjacentSystems = sourceRegion.adjacentSolarSystems().stream()
					.map(fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem::enName)
					.sorted()
					.toList();
			r.id = sourceRegion.id();
			r.isAbyssal = isAbyssal(sourceRegion.universe());
			r.isHidden = isHidden(sourceRegion.universe());
			r.isKS = isKs(sourceRegion.universe());
			r.isVoid = isVoid(sourceRegion.universe());
			r.isWormhole = isWh(sourceRegion.universe());
			r.name = sourceRegion.enName();

			// set jovian/pochven KS
			if (r.isKS) {
				if (r.id == 10000019// A821-A
						|| r.id == 10000017// J7HZ-F is jove ?
						|| r.id == 10000004// UUA-F4
				) {
					r.isJovian = true;
				}
				if (r.id == 10000070) {
					r.isPochven = true;
				}
			}
			regions.add(r);
			sourceRegion.constellations().forEach(sourceConstellation -> {
				Constellation c = new Constellation();
				c.adjacentConstellations = sourceConstellation.adjacentConstellations().stream()
						.map(fr.guiguilechat.jcelechat.libs.sde.model.locations.Constellation::enName)
						.sorted()
						.toList();
				c.adjacentRegions = sourceConstellation.adjacentRegions().stream()
						.map(fr.guiguilechat.jcelechat.libs.sde.model.locations.Region::enName)
						.sorted()
						.toList();
				c.adjacentSystems = sourceConstellation.adjacentSolarSystems().stream()
						.map(fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem::enName)
						.sorted()
						.toList();
				c.centerX = sourceConstellation.position().x;
				c.centerY = sourceConstellation.position().y;
				c.centerZ = sourceConstellation.position().z;
				c.id = sourceConstellation.id();
				c.region = r.name();
				c.isAbyssal = r.isAbyssal;
				c.isHidden = r.isHidden;
				c.isJovian = r.isJovian;
				c.isKS = r.isKS;
				c.isPochven = r.isPochven;
				c.isVoid = r.isVoid;
				c.isWormhole = r.isWormhole;
				c.name = sourceConstellation.enName();
				c.region = r.name;
				r.constellations.add(c.name);
				constellations.add(c);
				sourceConstellation.solarSystems().forEach(sourceSystem -> {
					SolarSystem s = new SolarSystem();
					s.adjacentConstellations = sourceSystem.adjacentConstellations().stream()
							.map(fr.guiguilechat.jcelechat.libs.sde.model.locations.Constellation::enName)
							.sorted()
							.toList();
					s.adjacentRegions = sourceSystem.adjacentRegions().stream()
							.map(fr.guiguilechat.jcelechat.libs.sde.model.locations.Region::enName)
							.sorted()
							.toList();
					s.adjacentSystems = sourceSystem.adjacentSolarSystems().stream()
							.map(fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem::enName)
							.sorted()
							.toList();
					s.anchorStructures = sourceSystem.disallowedAnchorCategories().isEmpty()
							&& sourceSystem.disallowedAnchorGroups().isEmpty();
					s.centerX = sourceSystem.position().x;
					s.centerY = sourceSystem.position().y;
					s.centerZ = sourceSystem.position().z;
					s.constellation = c.name();
					s.id = sourceSystem.id();
					s.isAbyssal = r.isAbyssal;
					s.isBorder = sourceSystem.border();
					c.hasBorder |= s.isBorder;
					s.isCorridor = sourceSystem.corridor();
					c.hasCorridor |= s.isCorridor;
					s.isFringe = sourceSystem.fringe();
					c.hasFringe |= s.isFringe;
					s.isHidden = r.isHidden;
					s.isHub = sourceSystem.hub();
					c.hasHub |= s.isHub;
					s.isJovian = r.isJovian;
					s.isKS = r.isKS;
					s.isPochven = r.isPochven;
					s.isVoid = r.isVoid;
					s.isWormhole = r.isWormhole;
					s.name = sourceSystem.enName();
					s.region = r.name;
					s.truesec = sourceSystem.securityStatus();
					c.systems.add(s.name);
					systems.add(s);
					sourceSystem.adjacentConstellations();
				});
				Collections.sort(c.systems);
			});
			Collections.sort(r.constellations);
		});
		fr.guiguilechat.jcelechat.libs.sde.model.locations.Station.CACHE.all().forEach(sourceStation -> {
			Station added = new Station();
			added.id = sourceStation.id();
			added.name = sourceStation.enName();
			added.services.addAll(sourceStation.operation().serviceNames());
			added.services.removeIf(s -> s == null || s.length() == 0);
			Collections.sort(added.services);
			added.solarSystemId = sourceStation.solarSystem().id();
			added.solarSystem = sourceStation.solarSystem().enName();
			stations.add(added);
		});
		return new LocationTranslation(regions, constellations, systems, stations);
	}

	static boolean isAbyssal(fr.guiguilechat.jcelechat.model.formula.space.Universe universe) {
		return universe == fr.guiguilechat.jcelechat.model.formula.space.Universe.Abyssal;
	}

	static boolean isHidden(fr.guiguilechat.jcelechat.model.formula.space.Universe universe) {
		return universe == fr.guiguilechat.jcelechat.model.formula.space.Universe.Hidden;
	}

	static boolean isKs(fr.guiguilechat.jcelechat.model.formula.space.Universe universe) {
		return universe == fr.guiguilechat.jcelechat.model.formula.space.Universe.Eve;
	}

	static boolean isVoid(fr.guiguilechat.jcelechat.model.formula.space.Universe universe) {
		return universe == fr.guiguilechat.jcelechat.model.formula.space.Universe.Void;
	}

	static boolean isWh(fr.guiguilechat.jcelechat.model.formula.space.Universe universe) {
		return universe == fr.guiguilechat.jcelechat.model.formula.space.Universe.WormHole;
	}
}
