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
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.TypeAttribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.CategoryService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeAttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Stargate;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult.STATUS;
import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EcategoryIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EdogmaAttributes;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EgroupIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeDogma;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Moon;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.NPCStation;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Planet;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SDEUpdateService {

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ConstellationService constellationService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private SolarSystemService solarsystemService;

	@Autowired
	private StargateService stargateService;

	@Autowired
	private StationService stationService;

	@Autowired
	private TypeAttributeService typeattributeService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private UpdateResultService updateresultService;

	@Value("${sde.updater.forcereinsert:false}")
	private boolean forceReinsert;

	@Transactional
	public void updateSDE() {
		Instant startDate = Instant.now();
		UpdateResult ur = UpdateResult.builder().startedDate(startDate).build();
		boolean alreadyInserted = updateresultService.alreadyInserted();
		File newFile = SDECache.INSTANCE.updateZip(alreadyInserted && !forceReinsert);
		Instant fetchedDate = Instant.now();
		ur.setFetchedDurationMs(fetchedDate.toEpochMilli() - startDate.toEpochMilli());
		if (newFile != null) {
			log.debug("updating DB from SDE file : " + newFile.getAbsolutePath());
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
		updateresultService.save(ur);
	}

	record GroupData(EgroupIDs group, int id) {
	}

	record TypeData(EtypeIDs type, int id) {
	}

	record TypeAttributeData(int typeId, int attributeId, Number value) {
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

	record StationData(fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.NPCStation data, int stationId,
			int solsysId) {
	}

	static class UpdateContext {
		public final List<Category> categories = new ArrayList<>();
		public final List<GroupData> groups = new ArrayList<>();
		public final List<Attribute> attributes = new ArrayList<>();
		public final List<TypeAttributeData> typeAttributes = new ArrayList<>();
		public final List<TypeData> types = new ArrayList<>();
		public final List<RegionData> regions = new ArrayList<>();
		public final List<ConstelData> constels = new ArrayList<>();
		public final List<SolarSystemData> systems = new ArrayList<>();
		public final List<StargateData> stargates = new ArrayList<>();
		public final List<StationData> stations = new ArrayList<>();
	}

	protected void updateFromFile(File newFile) throws ZipException, IOException {

		//
		// clear all
		//

		stationService.clear();
		stargateService.clear();
		solarsystemService.clear();
		constellationService.clear();
		regionService.clear();

		typeattributeService.clear();
		attributeService.clear();
		typeService.clear();
		groupService.clear();
		categoryService.clear();

		//
		// load
		//

		UpdateContext context = new UpdateContext();
		try (ZipFile zipFile = new ZipFile(newFile)) {
			for (ZipEntry zipentry : Collections.list(zipFile.entries())) {
				if (!zipentry.isDirectory()) {
					applyZipEntry(context, zipFile, zipentry);
				}
			}
		}
		log.info("loaded dogma : "
				+ context.categories.size() + " categories, "
				+ context.groups.size() + " groups, "
				+ context.types.size() + " types, "
				+ context.attributes.size() + " attributes, "
				+ context.typeAttributes.size() + " typeAttributes");
		log.info("loaded universe : "
				+ context.regions.size() + " regions, "
				+ context.constels.size() + " constellations, "
				+ context.systems.size() + " solar systems, "
				+ context.stargates.size() + " stargates, "
				+ context.stations.size() + " stations");

		//
		// insert
		// this is done in the opposite sense of clear
		//

		// dogma

		Map<Integer, Category> categoriesById = categoryService.saveAll(context.categories)
				.stream().collect(Collectors.toMap(Category::getCategoryId, c -> c));

		Map<Integer, Group> groupsById = groupService.saveAll(context.groups.stream()
				.map(gd -> Group.from(gd.id(), gd.group(), categoriesById.get(gd.group().categoryID))).toList())
				.stream().collect(Collectors.toMap(Group::getGroupId, g -> g));

		Map<Integer, Type> typesById = typeService.saveAll(context.types.stream()
				.map(td -> Type.from(td.id(), td.type(), groupsById.get(td.type().groupID))).toList())
				.stream().collect(Collectors.toMap(Type::getTypeId, t -> t));

		Map<Integer, Attribute> attributesById = attributeService.saveAll(context.attributes)
				.stream().collect(Collectors.toMap(Attribute::getAttributeId, c -> c));

		typeattributeService.saveAll(context.typeAttributes.stream()
				.map(tad -> TypeAttribute.from(typesById.get(tad.typeId()), attributesById.get(tad.attributeId()), tad.value()))
				.toList());

		// universe

		Map<String, Region> regionByName = regionService.saveAll(context.regions.stream()
				.map(rd -> Region.from(rd.data(), rd.name(), rd.universeName())).toList())
				.stream().collect(Collectors.toMap(Region::getName, r -> r));

		Map<String, Constellation> constelByName = constellationService.saveAll(context.constels.stream()
				.map(cd -> Constellation.from(cd.data(), cd.name(), regionByName.get(cd.regionName()))).toList())
				.stream().collect(Collectors.toMap(Constellation::getName, c -> c));

		Map<Integer, SolarSystem> sysById = solarsystemService.saveAll(context.systems.stream()
				.map(sd -> SolarSystem.from(sd.data(), sd.name(), constelByName.get(sd.constellationName()))).toList())
				.stream().collect(Collectors.toMap(SolarSystem::getSolarSystemId, s -> s));

		Map<Integer, Stargate> sgById = stargateService.saveAll(context.stargates.stream()
				.map(sd -> Stargate.from(sd.data(), sd.stargateId(), sysById.get(sd.solsysId()))).toList())
				.stream().collect(Collectors.toMap(Stargate::getStargateId, s -> s));
		for (StargateData sgd : context.stargates) {
			int id1 = sgd.stargateId();
			int id2 = sgd.data().destination;
			if (id1 < id2) {
				Stargate sg1 = sgById.get(id1);
				Stargate sg2 = sgById.get(id2);
				sg1.setDestination(sg2);
				sg2.setDestination(sg1);
			}
		}
		stargateService.saveAll(sgById.values());

		stationService.saveAll(context.stations.stream()
				.map(sd -> Station.from(sd.data(), sd.stationId(), sysById.get(sd.solsysId()))).toList());

	}

	static final Pattern ENTRYNAME_SOLARSYSTEM_PATTERN = Pattern.compile(
			"sde/fsd/universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/solarsystem\\.staticdata");

	static final Pattern ENTRYNAME_CONSTELLATION_PATTERN = Pattern.compile(
			"sde/fsd/universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/constellation\\.staticdata");

	static final Pattern ENTRYNAME_REGION_PATTERN = Pattern.compile(
			"sde/fsd/universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/region\\.staticdata");

	static final Pattern ENTRYNAME_CATEGORIES_PATTERN = Pattern.compile(
			"sde/fsd/categoryIDs\\.yaml");

	static final Pattern ENTRYNAME_GROUPS_PATTERN = Pattern.compile(
			"sde/fsd/groupIDs\\.yaml");

	static final Pattern ENTRYNAME_TYPES_PATTERN = Pattern.compile(
			"sde/fsd/typeIDs\\.yaml");

	static final Pattern ENTRYNAME_ATTRIBUTES_PATTERN = Pattern.compile(
			"sde/fsd/dogmaAttributes\\.yaml");

	static final Pattern ENTRYNAME_TYPEATTRIBUTES_PATTERN = Pattern.compile(
			"sde/fsd/typeDogma\\.yaml");

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
		if (ENTRYNAME_CATEGORIES_PATTERN.matcher(name).matches()) {
			appyCategories(context, is);
			return;
		}
		if (ENTRYNAME_GROUPS_PATTERN.matcher(name).matches()) {
			appyGroups(context, is);
			return;
		}
		if (ENTRYNAME_TYPES_PATTERN.matcher(name).matches()) {
			appyTypes(context, is);
			return;
		}
		if (ENTRYNAME_ATTRIBUTES_PATTERN.matcher(name).matches()) {
			appyAttributes(context, is);
			return;
		}
		if (ENTRYNAME_TYPEATTRIBUTES_PATTERN.matcher(name).matches()) {
			appyTypeAttributes(context, is);
			return;
		}
		// log.info("ignore entry " + name);
	}

	private void appyCategories(UpdateContext context, InputStream is) {
		context.categories
				.addAll(EcategoryIDs.from(is).entrySet().stream().map(Category::from).toList());
	}

	private void appyGroups(UpdateContext context, InputStream is) {
		context.groups
				.addAll(EgroupIDs.from(is).entrySet().stream().map(e -> new GroupData(e.getValue(), e.getKey())).toList());
	}

	private void appyTypes(UpdateContext context, InputStream is) {
		context.types
				.addAll(EtypeIDs.from(is).entrySet().stream().map(e -> new TypeData(e.getValue(), e.getKey())).toList());
	}

	private void appyAttributes(UpdateContext context, InputStream is) {
		context.attributes
				.addAll(
						EdogmaAttributes.from(is).entrySet().stream().map(e -> Attribute.from(e.getKey(), e.getValue())).toList());
	}

	private void appyTypeAttributes(UpdateContext context, InputStream is) {
		context.typeAttributes.addAll(
				EtypeDogma.from(is).entrySet().stream()
						.filter(e -> e.getValue().dogmaAttributes != null && e.getValue().dogmaAttributes.length > 0)
						.flatMap(e -> Stream.of(e.getValue().dogmaAttributes)
								.map(eat -> new TypeAttributeData(e.getKey(), eat.attributeID, eat.value)))
						.toList());
	}

	private void applyRegion(UpdateContext context, InputStream is, String uniName, String regionName) {
		context.regions.add(new RegionData(is, regionName, uniName));
	}

	private void applyConstellation(UpdateContext context, InputStream is, String uniName, String regionName,
			String constellationName) {
		context.constels.add(new ConstelData(is, constellationName, regionName));
	}

	private void applySolarSystem(UpdateContext context, InputStream is, String uniName, String regionName,
			String constellationName, String solarSystemName) {
		SolarSystemData ssd = new SolarSystemData(is, solarSystemName, constellationName);
		context.systems.add(ssd);
		for (Entry<Integer, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Stargate> sge : ssd
				.data().stargates.entrySet()) {
			context.stargates.add(new StargateData(sge.getValue(), sge.getKey(), ssd.data().solarSystemID));
		}
		for (Planet p : ssd.data().planets.values()) {
			for (Entry<Integer, NPCStation> e : p.npcStations.entrySet()) {
				context.stations.add(new StationData(e.getValue(), e.getKey(), ssd.data.solarSystemID));
			}
			for (Moon m : p.moons.values()) {
				for (Entry<Integer, NPCStation> e : m.npcStations.entrySet()) {
					context.stations.add(new StationData(e.getValue(), e.getKey(), ssd.data.solarSystemID));
				}
			}
		}
	}

}
