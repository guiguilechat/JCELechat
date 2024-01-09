package fr.guiguilechat.jcelechat.libs.spring.sde.updater.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Stargate;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult.STATUS;
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
	private SolarSystemService ssService;

	@Autowired
	private StargateService stService;

	@Autowired
	private UpdateResultService urService;

	@Value("${sde.updater.forcereinsert:false}")
	private boolean forceReinsert;

	@Transactional
	public void updateSDE() {
		Instant startDate = Instant.now();
		UpdateResult ur = UpdateResult.builder().startedDate(startDate).build();
		boolean alreadyInserted = urService.alreadyInserted();
		File newFile = SDECache.INSTANCE.updateZip(alreadyInserted && !forceReinsert);
		Instant fetchedDate = Instant.now();
		ur.setFetchedDurationMs(fetchedDate.toEpochMilli() - startDate.toEpochMilli());
		if(newFile!=null) {
			log.info("updating DB from SDE file : " + newFile.getAbsolutePath());
			try {
				updateFromFile(newFile);
				ur.setStatus(STATUS.SUCCESS);
			} catch (Exception e) {
				ur.setStatus(STATUS.FAIL);
				ur.setError(e.getMessage());
				log.error("while updating from SDE file " + newFile.getAbsolutePath(), e);
			}
		}
		Instant processedDate = Instant.now();
		ur.setProcessDurationMs(processedDate.toEpochMilli() - fetchedDate.toEpochMilli());
		urService.save(ur);
	}

	record RegionData(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region data, String name,
			String universeName) {
		RegionData(InputStream is, String name, String uniName) {
			this(new Yaml().loadAs(is, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Region.class), name, uniName);
		}
	}

	record ConstelData(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Constellation data, String name,
			String regionName) {
		ConstelData(InputStream is, String name, String regName) {
			this(new Yaml().loadAs(is, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.Constellation.class), name,
					regName);
		}
	}

	record SolarSystemData(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem data, String name,
			String constellationName) {
		SolarSystemData(InputStream is, String name, String cstName) {
			this(new Yaml().loadAs(is, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.class), name,
					cstName);
		}
	}

	record StargateData(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Stargate data, int stargateId,
			int solsysId) {

	}

	record UpdateContext(List<RegionData> regions,
			List<ConstelData> constels,
			List<SolarSystemData> systems,
			List<StargateData> stargates) {
		public UpdateContext() {
			this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
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
				+ " constellations, " + context.systems().size() + " solar systems, " + context.stargates().size()
				+ " stargates");

		stService.clear();
		ssService.clear();
		cService.clear();
		rService.clear();

		Map<String, Region> regionByName = context.regions().stream()
				.map(rd -> Region.from(rd.data(), rd.name(), rd.universeName()))
				.collect(Collectors.toMap(Region::getName, r -> r));
		rService.saveAll(regionByName.values());

		Map<String, Constellation> constelByName = context.constels().stream()
				.map(cd -> Constellation.from(cd.data(), cd.name(), regionByName.get(cd.regionName())))
				.collect(Collectors.toMap(Constellation::getName, c -> c));
		cService.saveAll(constelByName.values());

		Map<Integer, SolarSystem> sysById = context.systems().stream()
				.map(sd -> SolarSystem.from(sd.data(), sd.name(), constelByName.get(sd.constellationName())))
				.collect(Collectors.toMap(SolarSystem::getSolarSystemId, s -> s));
		ssService.saveAll(sysById.values());

		Map<Integer, Stargate> sgById = context.stargates().stream()
				.map(sd -> Stargate.from(sd.data(), sd.stargateId(), sysById.get(sd.solsysId())))
				.collect(Collectors.toMap(Stargate::getStargateId, s -> s));
		stService.saveAll(sgById.values());
		for (StargateData sgd : context.stargates()) {
			int id1 = sgd.stargateId();
			int id2 = sgd.data().destination;
			if (id1 < id2) {
				Stargate sg1 = sgById.get(id1);
				Stargate sg2 = sgById.get(id2);
				sg1.setDestination(sg2);
				sg2.setDestination(sg1);
			}
		}
		stService.saveAll(sgById.values());
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
		SolarSystemData ssd = new SolarSystemData(is, solarSystemName, constellationName);
		context.systems().add(ssd);
		for (Entry<Integer, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Stargate> sge : ssd
				.data().stargates.entrySet()) {
			context.stargates().add(new StargateData(sge.getValue(), sge.getKey(), ssd.data().solarSystemID));
		}
	}

}
