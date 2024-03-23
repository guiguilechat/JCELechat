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
		public final List<Attribute> attributes = new ArrayList<>();
		public final List<Eblueprints> blueprints = new ArrayList<>();
		public final List<Category> categories = new ArrayList<>();
		public final List<ConstelData> constels = new ArrayList<>();
		public final Map<Integer, String> invNames = new HashMap<>();
		public final List<GroupData> groups = new ArrayList<>();
		public final Map<Integer, EplanetSchematics> planetSchematics = new HashMap<>();
		public final List<RegionData> regions = new ArrayList<>();
		public final List<StargateData> stargates = new ArrayList<>();
		public final List<StationData> stations = new ArrayList<>();
		public final List<SolarSystemData> systems = new ArrayList<>();
		public final List<TypeAttributeData> typeAttributes = new ArrayList<>();
		public final List<TypeData> types = new ArrayList<>();
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
					applyZipEntry(context, zipFile, zipentry);
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
		// clear all
		//

		stationService.clear();
		stargateService.clear();
		solarsystemService.clear();
		constellationService.clear();
		regionService.clear();

		schemProductService.clear();
		schemMaterialService.clear();
		schematicService.clear();
		materialService.clear();
		productService.clear();
		skillService.clear();
		blueprintActivityService.clear();
		typeattributeService.clear();
		attributeService.clear();
		typeService.clear();
		groupService.clear();
		categoryService.clear();

		log.info(" cleared SDE DB");

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
				.map(sd -> Station.from(sd.data(), sd.stationId(), sysById.get(sd.solsysId()),
						context.invNames.get(sd.stationId())))
				.toList());

		List.of(BlueprintActivityService.CACHE_LIST,
				MaterialService.CACHE_LIST,
				ProductService.CACHE_LIST,
				SkillReqService.CACHE_LIST,
				StargateService.CACHE_LIST).stream().flatMap(List::stream).toList()
				.forEach(cacheName -> {
					cacheManager.getCache(cacheName).clear();
				});
		log.info(" finished updating SDE DB.");

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
		if (ENTRYNAME_BLUEPRINTS_PATTERN.matcher(name).matches()) {
			appyBlueprints(context, is);
			return;
		}
		if (ENTRYNAME_INVNAMES_PATTERN.matcher(name).matches()) {
			applyInvNames(context, is);
			return;
		}
		if (ENTRYNAME_PLANETSCHEMATICS_PATTERN.matcher(name).matches()) {
			applySchematics(context, is);
			return;
		}
		// log.info("ignore entry " + name);
	}

	private void applySchematics(UpdateContext context, InputStream is) {
		context.planetSchematics.putAll(EplanetSchematics.from(is));
	}

	private void applyInvNames(UpdateContext context, InputStream is) {
		context.invNames.putAll(EinvNames.from(is).stream().collect(Collectors.toMap(in -> in.itemID, in -> in.itemName)));
	}

	private void appyBlueprints(UpdateContext context, InputStream is) {
		context.blueprints.addAll(Eblueprints.from(is).values());
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
