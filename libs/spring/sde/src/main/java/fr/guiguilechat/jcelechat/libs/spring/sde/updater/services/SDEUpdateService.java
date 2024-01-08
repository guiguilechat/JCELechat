package fr.guiguilechat.jcelechat.libs.spring.sde.updater.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.services.SDEUpdateService.ConstelData;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.services.SDEUpdateService.RegionData;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.services.SDEUpdateService.SolarSystemData;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.services.SDEUpdateService.UpdateContext;
import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SDEUpdateService {

	@Autowired
	private ConstellationService cService;

	@Autowired
	private RegionService rService;

	@Autowired
	private SolarSystemService sService;

	@Autowired
	private UpdateResultService urService;

	@Transactional
	public void updateSDE() {
		UpdateResult ur = UpdateResult.builder().started(Instant.now()).build();
		boolean alreadyInserted =
				//
				false;
// urService.alreadyInserted();
		File newFile = SDECache.INSTANCE.updateZip(alreadyInserted);
		ur.setFetchedDate(Instant.now());
		if(newFile!=null) {
			log.info("updating DB from new SDE file : " + newFile.getAbsolutePath());
			try {
				updateFromFile(newFile);
				ur.setStatus(STATUS.SUCCESS);
			} catch (Exception e) {
				ur.setStatus(STATUS.FAIL);
				ur.setError(e.getMessage());
			}
		}
		urService.save(ur);
	}

	record RegionData(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region data, String name,
			String universeName) {
		RegionData(InputStream is, String name, String uniName) {
			this(new Yaml().loadAs(is, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region.class), name, uniName);
		}
	};

	record ConstelData(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Constellation data, String name,
			String regionName) {
		ConstelData(InputStream is, String name, String regName) {
			this(new Yaml().loadAs(is, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Constellation.class), name,
					regName);
		}
	};

	record SolarSystemData(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem data, String name,
			String constellationName) {
		SolarSystemData(InputStream is, String name, String cstName) {
			this(new Yaml().loadAs(is, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.class), name,
					cstName);
		}
	};

	record UpdateContext(List<RegionData> regions,
			List<ConstelData> constels,
			List<SolarSystemData> systems) {
		public UpdateContext() {
			this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		}
	}

	protected void updateFromFile(File newFile) throws ZipException, IOException {
		UpdateContext context = new UpdateContext();
		try (ZipFile zipFile = new ZipFile(newFile)) {
			for (ZipEntry zipentry : Collections.list(zipFile.entries())) {
				if (!zipentry.isDirectory()) {
					applyZipEntry(context, zipFile, zipentry);
				}
			}
		}
		log.info("post update, have loaded " + context.regions().size() + " regions, " + context.constels().size()
				+ " constellations, " + context.systems().size() + " solar systems");

		sService.clear();
		cService.clear();
		rService.clear();

		Map<String, Region> regionsByName = context.regions().stream()
				.map(rd -> Region.from(rd.data(), rd.name(), rd.universeName()))
				.collect(Collectors.toMap(r -> r.getName(), r -> r));
		rService.saveAll(regionsByName.values());

		Map<String, Constellation> constelsByName = context.constels().stream()
				.map(cd -> Constellation.from(cd.data(), cd.name(), regionsByName.get(cd.regionName())))
				.collect(Collectors.toMap(c -> c.getName(), c -> c));
		cService.saveAll(constelsByName.values());

		Map<String, SolarSystem> sysByName = context.systems().stream()
				.map(sd -> SolarSystem.from(sd.data(), sd.name(), constelsByName.get(sd.constellationName())))
				.collect(Collectors.toMap(s -> s.getName(), s -> s));
		sService.saveAll(sysByName.values());
	}

	static final Pattern ENTRYNAME_SOLARSYSTEM_PATTERN = Pattern.compile(
			"sde/fsd/universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/solarsystem\\.staticdata");

	static final Pattern ENTRYNAME_CONSTELLATION_PATTERN = Pattern.compile(
			"sde/fsd/universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/constellation\\.staticdata");

	static final Pattern ENTRYNAME_REGION_PATTERN = Pattern.compile(
			"sde/fsd/universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/region\\.staticdata");

	private void applyZipEntry(UpdateContext context, ZipFile zipFile, ZipEntry zipentry) throws IOException {
		String name = zipentry.getName();
		InputStream is = zipFile.getInputStream(zipentry);
		Matcher m = null;
		m = ENTRYNAME_SOLARSYSTEM_PATTERN.matcher(name);
		if (m.matches()) {
			applySolarSystem(context, is, m.group(1), m.group(2),
					m.group(3), m.group(4));
			return;
		}
		m = ENTRYNAME_CONSTELLATION_PATTERN.matcher(name);
		if (m.matches()) {
			applyConstellation(context, is, m.group(1), m.group(2),
					m.group(3));
			return;
		}
		m = ENTRYNAME_REGION_PATTERN.matcher(name);
		if (m.matches()) {
			applyRegion(context, is, m.group(1), m.group(2));
			return;
		}
		// log.info("ignore entry " + name);
	}

	private void applyRegion(UpdateContext context, InputStream is, String uniName, String regionName) {
		context.regions().add(new RegionData(is, regionName, uniName));
	}

	private void applyConstellation(UpdateContext context, InputStream is, String uniName, String regionName,
			String constellationName) {
		context.constels().add(new ConstelData(is, constellationName, regionName));
	}

	private void applySolarSystem(UpdateContext context, InputStream is, String uniName, String regionName,
			String constellationName, String solarSystemName) {
		context.systems().add(new SolarSystemData(is, solarSystemName, constellationName));
	}

}
