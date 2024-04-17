package fr.guiguilechat.jcelechat.libs.spring.sde.updater.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.BlueprintActivity;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.Product;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.model.SkillReq;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.BlueprintActivityService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.MaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.ProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.blueprint.services.SkillReqService;
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
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.SchemMaterial;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.SchemProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.Schematic;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.services.SchemMaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.services.SchemProductService;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.services.SchematicService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Constellation;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Stargate;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.ConstellationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.PlanetService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.SolarSystemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StationService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult.STATUS;
import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.SDECache.SDEDownload;
import fr.guiguilechat.jcelechat.model.sde.load.bsd.EinvNames;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EcategoryIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EdogmaAttributes;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EgroupIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EplanetSchematics;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeDogma;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Moon;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.NPCStation;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Planet;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SDEUpdateService {

	final private CacheManager cacheManager;

	final private AttributeService attributeService;

	final private BlueprintActivityService blueprintActivityService;

	final private CategoryService categoryService;

	final private ConstellationService constellationService;

	final private GroupService groupService;

	final private MaterialService materialService;

	final private PlanetService planetService;

	final private ProductService productService;

	final private RegionService regionService;

	final private SchematicService schematicService;

	final private SchemMaterialService schemMaterialService;

	final private SchemProductService schemProductService;

	final private SkillReqService skillService;

	final private SolarSystemService solarsystemService;

	final private StargateService stargateService;

	final private StationService stationService;

	final private TypeAttributeService typeattributeService;

	final private TypeService typeService;

	final private UpdateResultService updateresultService;

	/** interface for the beans that should react to SDE update */
	public static interface SdeUpdateListener {

		public default List<String> listSDECaches() {
			return List.of();
		}

		public default void onSDEUpdate() {

		}
	}

	private final List<SdeUpdateListener> updateListeners;

	@Value("${sde.updater.forcereinsert:false}")
	private boolean forceReinsert;

	@Transactional
	public void updateSDE() throws IOException {
		Instant startDate = Instant.now();
		UpdateResult ur = UpdateResult.builder().startedDate(startDate).build();
		UpdateResult lastSuccess = updateresultService.lastSuccess();
		SDEDownload fetch = SDECache.getSDE(lastSuccess != null ? lastSuccess.getEtag() : null);
		Instant fetchedDate = Instant.now();
		ur.setFetchedDurationMs(fetchedDate.toEpochMilli() - startDate.toEpochMilli());
		if (fetch.channel() != null) {
			File newFile = fetch.toTempFile();
			try {
				updateFromFile(newFile);
				ur.setStatus(STATUS.SUCCESS);
				ur.setEtag(fetch.etag());
			} catch (Exception e) {
				ur.setStatus(STATUS.FAIL);
				ur.setError(e.getMessage());
				log.error("while updating from SDE file " + newFile.getAbsolutePath(), e);
			}
		} else if (fetch.error() != null) {
			ur.setStatus(STATUS.FAIL);
			ur.setError(fetch.error().getMessage());
		}
		Instant processedDate = Instant.now();
		ur.setProcessDurationMs(processedDate.toEpochMilli() - fetchedDate.toEpochMilli());
		updateresultService.save(ur);
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

	public static record PlanetSystemData(Planet planet, int solarSystemId) {
	}

	/** store the SDE lines as required to build them into memory */
	class UpdateContext {
		public final Map<Integer, EdogmaAttributes> attributes = new HashMap<>();
		public final List<Eblueprints> blueprints = new ArrayList<>();
		public final Map<Integer, EcategoryIDs> categories = new HashMap<>();
		public final List<ConstelData> constels = new ArrayList<>();
		public final Map<Long, String> invNames = new HashMap<>();
		public final Map<Integer, EgroupIDs> groups = new HashMap<>();
		public final Map<Long, PlanetSystemData> planets = new HashMap<>();
		public final Map<Integer, EplanetSchematics> planetSchematics = new HashMap<>();
		public final List<RegionData> regions = new ArrayList<>();
		public final List<StargateData> stargates = new ArrayList<>();
		public final List<StationData> stations = new ArrayList<>();
		public final List<SolarSystemData> systems = new ArrayList<>();
		public final List<TypeAttributeData> typeAttributes = new ArrayList<>();
		public final Map<Integer, EtypeIDs> types = new HashMap<>();

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

		static final Pattern ENTRYNAME_BLUEPRINTS_PATTERN = Pattern.compile(
				"sde/fsd/blueprints\\.yaml");

		static final Pattern ENTRYNAME_TYPEATTRIBUTES_PATTERN = Pattern.compile(
				"sde/fsd/typeDogma\\.yaml");

		static final Pattern ENTRYNAME_INVNAMES_PATTERN = Pattern.compile(
				"sde/bsd/invNames\\.yaml");

		static final Pattern ENTRYNAME_PLANETSCHEMATICS_PATTERN = Pattern.compile(
				"sde/fsd/planetSchematics\\.yaml");

		private void applyZipEntry(ZipFile zipFile, ZipEntry zipentry) throws IOException {
			String name = zipentry.getName();
			InputStream is = zipFile.getInputStream(zipentry);
			Matcher m = null;
			m = ENTRYNAME_SOLARSYSTEM_PATTERN.matcher(name);
			if (m.matches()) {
				saveSolarSystem(is, m.group(1), m.group(2),
						m.group(3), m.group(4));
				return;
			}
			m = ENTRYNAME_CONSTELLATION_PATTERN.matcher(name);
			if (m.matches()) {
				saveConstellation(is, m.group(1), m.group(2),
						m.group(3));
				return;
			}
			m = ENTRYNAME_REGION_PATTERN.matcher(name);
			if (m.matches()) {
				saveRegion(is, m.group(1), m.group(2));
				return;
			}
			if (ENTRYNAME_CATEGORIES_PATTERN.matcher(name).matches()) {
				saveCategories(is);
				return;
			}
			if (ENTRYNAME_GROUPS_PATTERN.matcher(name).matches()) {
				saveGroups(is);
				return;
			}
			if (ENTRYNAME_TYPES_PATTERN.matcher(name).matches()) {
				saveTypes(is);
				return;
			}
			if (ENTRYNAME_ATTRIBUTES_PATTERN.matcher(name).matches()) {
				saveAttributes(is);
				return;
			}
			if (ENTRYNAME_TYPEATTRIBUTES_PATTERN.matcher(name).matches()) {
				saveTypeAttributes(is);
				return;
			}
			if (ENTRYNAME_BLUEPRINTS_PATTERN.matcher(name).matches()) {
				saveBlueprints(is);
				return;
			}
			if (ENTRYNAME_INVNAMES_PATTERN.matcher(name).matches()) {
				saveInvNames(is);
				return;
			}
			if (ENTRYNAME_PLANETSCHEMATICS_PATTERN.matcher(name).matches()) {
				saveSchematics(is);
				return;
			}
			// log.info("ignore entry " + name);
		}

		private void saveSchematics(InputStream is) {
			planetSchematics.putAll(EplanetSchematics.from(is));
		}

		private void saveInvNames(InputStream is) {
			invNames.putAll(EinvNames.from(is).stream().collect(Collectors.toMap(in -> (long) in.itemID, in -> in.itemName)));
		}

		private void saveBlueprints(InputStream is) {
			blueprints.addAll(Eblueprints.from(is).values());
		}

		private void saveCategories(InputStream is) {
			categories.putAll(EcategoryIDs.from(is));
		}

		private void saveGroups(InputStream is) {
			groups.putAll(EgroupIDs.from(is));
		}

		private void saveTypes(InputStream is) {
			types.putAll(EtypeIDs.from(is));
		}

		private void saveAttributes(InputStream is) {
			attributes.putAll(EdogmaAttributes.from(is));
		}

		private void saveTypeAttributes(InputStream is) {
			typeAttributes.addAll(
					EtypeDogma.from(is).entrySet().stream()
							.filter(e -> e.getValue().dogmaAttributes != null && e.getValue().dogmaAttributes.length > 0)
							.flatMap(e -> Stream.of(e.getValue().dogmaAttributes)
									.map(eat -> new TypeAttributeData(e.getKey(), eat.attributeID, eat.value)))
							.toList());
		}

		private void saveRegion(InputStream is, String uniName, String regionName) {
			regions.add(new RegionData(is, regionName, uniName));
		}

		private void saveConstellation(InputStream is, String uniName, String regionName,
				String constellationName) {
			constels.add(new ConstelData(is, constellationName, regionName));
		}

		private void saveSolarSystem(InputStream is, String uniName, String regionName,
				String constellationName, String solarSystemName) {
			SolarSystemData ssd = new SolarSystemData(is, solarSystemName, constellationName);
			systems.add(ssd);
			for (Entry<Integer, fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem.Stargate> sge : ssd
					.data().stargates.entrySet()) {
				stargates.add(new StargateData(sge.getValue(), sge.getKey(), ssd.data().solarSystemID));
			}
			for (Entry<Long, Planet> ep : ssd.data().planets.entrySet()) {
				Planet p = ep.getValue();
				planets.put(ep.getKey(), new PlanetSystemData(p, ssd.data.solarSystemID));
				for (Entry<Integer, NPCStation> e : p.npcStations.entrySet()) {
					stations.add(new StationData(e.getValue(), e.getKey(), ssd.data.solarSystemID));
				}
				for (Moon m : p.moons.values()) {
					for (Entry<Integer, NPCStation> e : m.npcStations.entrySet()) {
						stations.add(new StationData(e.getValue(), e.getKey(), ssd.data.solarSystemID));
					}
				}
			}
		}
	}

	/**
	 * update the DB from a new zip SDE to apply.
	 */
	protected void updateFromFile(File newZipFile) throws ZipException, IOException {

		log.info("start update SDE DB from " + newZipFile.getAbsolutePath());

		//
		// load
		//

		UpdateContext context = new UpdateContext();
		try (ZipFile zipFile = new ZipFile(newZipFile)) {
			for (ZipEntry zipentry : Collections.list(zipFile.entries())) {
				if (!zipentry.isDirectory()) {
					context.applyZipEntry(zipFile, zipentry);
				}
			}
		}
		log.info(" loaded dogma : "
				+ context.categories.size() + " categories, "
				+ context.groups.size() + " groups, "
				+ context.types.size() + " types, "
				+ context.attributes.size() + " attributes, "
				+ context.typeAttributes.size() + " typeAttributes, "
				+ context.blueprints.size() + " blueprints");
		log.info(" loaded universe : "
				+ context.regions.size() + " regions, "
				+ context.constels.size() + " constellations, "
				+ context.systems.size() + " solar systems, "
				+ context.stargates.size() + " stargates, "
				+ context.stations.size() + " stations");

		//
		// wipe all that will be reinserted
		//

		schemProductService.clear();
		schemMaterialService.clear();
		schematicService.clear();
		materialService.clear();
		productService.clear();
		skillService.clear();
		blueprintActivityService.clear();
		typeattributeService.clear();

		log.info(" cleared SDE DB");

		//
		// insert
		// this is done in the opposite sense of clear
		//

		// dogma

		Map<Integer, Category> categoriesById = updateCategories(context);

		Map<Integer, Group> groupsById = updateGroups(context, categoriesById);

		Map<Integer, Type> typesById = updateTypes(context, groupsById);

		Map<Integer, Attribute> attributesById = updateAttributes(context);

		Map<Integer, List<TypeAttribute>> attributesByTypeId = typeattributeService.saveAll(context.typeAttributes.stream()
				.map(tad -> TypeAttribute.from(typesById.get(tad.typeId()), attributesById.get(tad.attributeId()), tad.value()))
				.toList()).stream().collect(Collectors.groupingBy(ta -> ta.getType().getTypeId()));

		// blueprints
		// first create all the activities that exist for each blueprint, store them by
		// bp id, then deduce the material, products, skills entries for those
		// activities

		Map<Integer, BlueprintActivity> copyingByBpId = blueprintActivityService.saveAll(
				context.blueprints.stream()
						.filter(bp -> bp.activities.copying.active())
						.map(ebp -> BlueprintActivity.of(ebp, ActivityType.copying, typesById.get(ebp.blueprintTypeID)))
						.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getTypeId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> inventionByBpId = blueprintActivityService.saveAll(
				context.blueprints.stream()
						.filter(bp -> bp.activities.invention.active())
						.map(ebp -> BlueprintActivity.of(ebp, ActivityType.invention, typesById.get(ebp.blueprintTypeID)))
						.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getTypeId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> manufacturingByBpId = blueprintActivityService.saveAll(
				context.blueprints.stream()
						.filter(bp -> bp.activities.manufacturing.active())
						.map(ebp -> BlueprintActivity.of(ebp, ActivityType.manufacturing,
								typesById.get(ebp.blueprintTypeID)))
						.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getTypeId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> reactionByBpId = blueprintActivityService.saveAll(
				context.blueprints.stream()
						.filter(bp -> bp.activities.reaction.active())
						.map(ebp -> BlueprintActivity.of(ebp, ActivityType.reaction,
								typesById.get(ebp.blueprintTypeID)))
						.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getTypeId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> researchMatByBpId = blueprintActivityService.saveAll(
				context.blueprints.stream()
						.filter(bp -> bp.activities.research_material.active())
						.map(ebp -> BlueprintActivity.of(ebp, ActivityType.research_material,
								typesById.get(ebp.blueprintTypeID)))
						.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getTypeId(), bpa -> bpa));

		Map<Integer, BlueprintActivity> researchTimeByBpId = blueprintActivityService.saveAll(
				context.blueprints.stream()
						.filter(bp -> bp.activities.research_time.active())
						.map(ebp -> BlueprintActivity.of(ebp, ActivityType.research_time,
								typesById.get(ebp.blueprintTypeID)))
						.toList())
				.stream().collect(Collectors.toMap(bpa -> bpa.getType().getTypeId(), bpa -> bpa));

		List<Material> newMaterials = new ArrayList<>();
		List<Product> newProducts = new ArrayList<>();
		List<SkillReq> newSkills = new ArrayList<>();
		for (Eblueprints ebp : context.blueprints) {
			addActivityData(copyingByBpId.get(ebp.blueprintTypeID), ebp.activities.copying, typesById, newMaterials,
					newProducts, newSkills);
			addActivityData(inventionByBpId.get(ebp.blueprintTypeID), ebp.activities.invention, typesById,
					newMaterials, newProducts, newSkills);
			addActivityData(manufacturingByBpId.get(ebp.blueprintTypeID), ebp.activities.manufacturing, typesById,
					newMaterials, newProducts, newSkills);
			addActivityData(reactionByBpId.get(ebp.blueprintTypeID), ebp.activities.reaction, typesById,
					newMaterials, newProducts, newSkills);
			addActivityData(researchMatByBpId.get(ebp.blueprintTypeID), ebp.activities.research_material, typesById,
					newMaterials, newProducts, newSkills);
			addActivityData(researchTimeByBpId.get(ebp.blueprintTypeID), ebp.activities.research_time, typesById,
					newMaterials, newProducts, newSkills);
		}
		materialService.saveAll(newMaterials);
		productService.saveAll(newProducts);
		skillService.saveAll(newSkills);

		schematicService
				.saveAll(context.planetSchematics.entrySet().stream().map(e -> {
					Schematic ret = Schematic.of(e.getValue(), e.getKey());
					List<Integer> pins = e.getValue().pins;
					if (pins != null && !pins.isEmpty()) {
						List<TypeAttribute> pipAtts = attributesByTypeId.get(pins.get(0));
						TypeAttribute pgAtt = pipAtts.stream().filter(ta -> ta.getAttribute().getAttributeId() == 15)
								.findFirst().orElse(null);
						if (pgAtt != null) {
							ret.setPowerLoad(pgAtt.getAttValue().intValue());
						}
						TypeAttribute cpuLoadAtt = pipAtts.stream().filter(ta -> ta.getAttribute().getAttributeId() == 49)
								.findFirst().orElse(null);
						if (cpuLoadAtt != null) {
							ret.setCpuLoad(cpuLoadAtt.getAttValue().intValue());
						}
					}
					ret.setMaterials(e.getValue().types.entrySet().stream()
							.filter(entry -> entry.getValue().isInput)
							.map(entry -> SchemMaterial.builder()
									.schematic(ret)
									.quantity(entry.getValue().quantity)
									.type(typesById.get(entry.getKey()))
									.build())
							.toList());
					ret.setProducts(e.getValue().types.entrySet().stream()
							.filter(entry -> !entry.getValue().isInput)
							.map(entry -> SchemProduct.builder()
									.schematic(ret)
									.quantity(entry.getValue().quantity)
									.type(typesById.get(entry.getKey()))
									.build())
							.toList());
					return ret;
				}).toList());

		// universe

		Map<String, Region> regionsByName = updateRegions(context);

		Map<String, Constellation> constellationsByName = updateConstellations(context, regionsByName);

		Map<Integer, SolarSystem> solarSystemsById = updateSolarSystems(context, constellationsByName);

		updatePlanets(context, solarSystemsById, typesById);

		updateStargates(context, solarSystemsById, typesById);

		updateStations(context, solarSystemsById, typesById);

		// listeners

		updateListeners.stream().flatMap(l -> l.listSDECaches().stream())
				.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
		updateListeners.stream().forEach(SdeUpdateListener::onSDEUpdate);

		log.info(" finished updating SDE DB.");

	}

	protected Map<Integer, Category> updateCategories(UpdateContext context) {
		Map<Integer, Category> alreadyPresents = categoryService.allById();
		List<Category> created = new ArrayList<>();
		// update already saved categories, create missing ones.
		for (Entry<Integer, EcategoryIDs> entry : context.categories.entrySet()) {
			int id = entry.getKey();
			EcategoryIDs data = entry.getValue();
			Category present = alreadyPresents.get(id);
			if (present == null) {
				created.add(Category.from(id, data));
			} else {
				present.update(data);
			}
		}
		// so far ignore removed ones.
		return Stream.concat(
				categoryService.saveAll(created).stream(),
				categoryService.saveAll(alreadyPresents.values()).stream())
				.collect(Collectors.toMap(Category::getCategoryId, c -> c));
	}

	protected Map<Integer, Group> updateGroups(UpdateContext context, Map<Integer, Category> categories) {
		Map<Integer, Group> alreadyPresents = groupService.allById();
		List<Group> created = new ArrayList<>();
		for (Entry<Integer, EgroupIDs> entry : context.groups.entrySet()) {
			int id = entry.getKey();
			EgroupIDs data = entry.getValue();
			Group present = alreadyPresents.get(id);
			Category parent = categories.get(data.categoryID);
			if (present == null) {
				created.add(Group.from(id, data, parent));
			} else {
				present.update(data, parent);
			}
		}
		return Stream.concat(
				groupService.saveAll(created).stream(),
				groupService.saveAll(alreadyPresents.values()).stream())
				.collect(Collectors.toMap(Group::getGroupId, c -> c));
	}

	protected Map<Integer, Type> updateTypes(UpdateContext context, Map<Integer, Group> groups) {
		Map<Integer, Type> alreadyPresents = typeService.allById();
		List<Type> created = new ArrayList<>();
		for (Entry<Integer, EtypeIDs> entry : context.types.entrySet()) {
			int id = entry.getKey();
			EtypeIDs data = entry.getValue();
			Type present = alreadyPresents.get(id);
			Group parent = groups.get(data.groupID);
			if (present == null) {
				created.add(Type.from(id, data, parent));
			} else {
				present.update(data, parent);
			}
		}
		return Stream.concat(
				typeService.saveAll(created).stream(),
				typeService.saveAll(alreadyPresents.values()).stream())
				.collect(Collectors.toMap(Type::getTypeId, c -> c));
	}

	private Map<Integer, Attribute> updateAttributes(UpdateContext context) {
		Map<Integer, Attribute> alreadyPresents = attributeService.allById();
		List<Attribute> created = new ArrayList<>();
		for (Entry<Integer, EdogmaAttributes> entry : context.attributes.entrySet()) {
			int id = entry.getKey();
			EdogmaAttributes data = entry.getValue();
			Attribute present = alreadyPresents.get(id);
			if (present == null) {
				created.add(Attribute.from(id, data));
			} else {
				present.update(data);
			}
		}
		return Stream.concat(
				attributeService.saveAll(created).stream(),
				attributeService.saveAll(alreadyPresents.values()).stream())
				.collect(Collectors.toMap(Attribute::getAttributeId, c -> c));
	}

	void addActivityData(
			BlueprintActivity bpa,
			Activity act,
			Map<Integer, Type> typesById,
			List<Material> newMaterials,
			List<Product> newProducts,
			List<SkillReq> newSkills) {
		if (bpa != null) {
			newMaterials.addAll(act.materials.stream()
					.map(m -> Material.of(bpa, typesById.get(m.typeID), m.quantity)).toList());
			newProducts.addAll(act.products.stream()
					.map(p -> Product.of(bpa, typesById.get(p.typeID), p.probability, p.quantity)).toList());
			newSkills.addAll(act.skills.stream()
					.map(s -> SkillReq.of(bpa, typesById.get(s.typeID), s.level)).toList());
		}
	}

	private Map<String, Region> updateRegions(UpdateContext context) {
		Map<Integer, Region> alreadyPresents = regionService.allById();
		List<Region> created = new ArrayList<>();
		for (RegionData data : context.regions) {
			int id = data.data.regionID;
			Region present = alreadyPresents.get(id);
			if (present == null) {
				created.add(Region.from(data.data, data.name, data.universeName));
			} else {
				present.update(data.data, data.name, data.universeName);
			}
		}
		return Stream.concat(
				regionService.saveAll(created).stream(),
				regionService.saveAll(alreadyPresents.values()).stream())
				.collect(Collectors.toMap(Region::getName, c -> c));
	}

	private Map<String, Constellation> updateConstellations(UpdateContext context, Map<String, Region> regionsByName) {
		Map<Integer, Constellation> alreadyPresents = constellationService.allById();
		List<Constellation> created = new ArrayList<>();
		for (ConstelData data : context.constels) {
			int id = data.data.constellationID;
			Constellation present = alreadyPresents.get(id);
			Region parent = regionsByName.get(data.regionName);
			if (present == null) {
				created.add(Constellation.from(data.data, data.name, parent));
			} else {
				present.update(data.data, data.name, parent);
			}
		}
		return Stream.concat(
				constellationService.saveAll(created).stream(),
				constellationService.saveAll(alreadyPresents.values()).stream())
				.collect(Collectors.toMap(Constellation::getName, c -> c));
	}

	private Map<Integer, SolarSystem> updateSolarSystems(UpdateContext context,
			Map<String, Constellation> constellationsByName) {
		Map<Integer, SolarSystem> alreadyPresents = solarsystemService.allById();
		List<SolarSystem> created = new ArrayList<>();
		for (SolarSystemData data : context.systems) {
			int id = data.data.solarSystemID;
			SolarSystem present = alreadyPresents.get(id);
			Constellation parent = constellationsByName.get(data.constellationName);
			if (present == null) {
				created.add(SolarSystem.from(data.data, data.name, parent));
			} else {
				present.update(data.data, data.name, parent);
			}
		}
		return Stream.concat(
				solarsystemService.saveAll(created).stream(),
				solarsystemService.saveAll(alreadyPresents.values()).stream())
				.collect(Collectors.toMap(SolarSystem::getSolarSystemId, c -> c));
	}

	private Map<Long, fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Planet> updatePlanets(
			UpdateContext context, Map<Integer, SolarSystem> solarSystemsById, Map<Integer, Type> typesById) {
		Map<Long, fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Planet> alreadyPresents = planetService
				.allById();
		List<fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Planet> created = new ArrayList<>();
		for (Entry<Long, PlanetSystemData> entry : context.planets.entrySet()) {
			long id = entry.getKey();
			PlanetSystemData data = entry.getValue();
			fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Planet present = alreadyPresents.get(id);
			SolarSystem parent = solarSystemsById.get(entry.getValue().solarSystemId);
			String name = context.invNames.get(id);
			Type type = typesById.get((int) id);
			if (present == null) {
				created
						.add(fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Planet.from(id, data.planet(), name, parent,
								type));
			} else {
				present.update(data.planet(), name, parent, type);
			}
		}
		return Stream.concat(
				planetService.saveAll(created).stream(),
				planetService.saveAll(alreadyPresents.values()).stream())
				.collect(
						Collectors.toMap(fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Planet::getPlanetId, c -> c));
	}

	private Map<Integer, Station> updateStations(
			UpdateContext context, Map<Integer, SolarSystem> solarSystemsById, Map<Integer, Type> typesById) {
		Map<Integer, Station> alreadyPresents = stationService
				.allById();
		List<Station> created = new ArrayList<>();
		for (StationData data : context.stations) {
			int id = data.stationId;
			Station present = alreadyPresents.get(id);
			SolarSystem parent = solarSystemsById.get(data.solsysId);
			Type type = typesById.get(data.data.typeID);
			String name = context.invNames.get((long) id);
			if (present == null) {
				created
						.add(Station.from(id, data.data, name, parent, type));
			} else {
				present.update(data.data, name, parent, type);
			}
		}
		return Stream.concat(
				stationService.saveAll(created).stream(),
				stationService.saveAll(alreadyPresents.values()).stream())
				.collect(
						Collectors.toMap(Station::getStationId, c -> c));
	}

	private Map<Integer, Stargate> updateStargates(UpdateContext context, Map<Integer, SolarSystem> solarSystemsById,
			Map<Integer, Type> typesById) {
		Map<Integer, Stargate> alreadyPresents = stargateService
				.allById();
		List<Stargate> created = new ArrayList<>();
		for (StargateData data : context.stargates) {
			int id = data.stargateId;
			Stargate present = alreadyPresents.get(id);
			SolarSystem parent = solarSystemsById.get(data.solsysId);
			Type type = typesById.get(data.data.typeID);
			if (present == null) {
				created
						.add(Stargate.from(id, data.data, parent, type));
			} else {
				present.update(data.data, parent, type);
			}
		}
		Map<Integer, Stargate> ret = Stream.concat(
				stargateService.saveAll(created).stream(),
				stargateService.saveAll(alreadyPresents.values()).stream())
				.collect(
						Collectors.toMap(Stargate::getStargateId, c -> c));

		for (StargateData sgd : context.stargates) {
			int id1 = sgd.stargateId();
			int id2 = sgd.data().destination;
			if (id1 < id2) {
				Stargate sg1 = ret.get(id1);
				Stargate sg2 = ret.get(id2);
				sg1.setDestination(sg2);
				sg2.setDestination(sg1);
			}
		}
		stargateService.saveAll(ret.values());
		return ret;
	}

}
