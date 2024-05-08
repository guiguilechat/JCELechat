package fr.guiguilechat.jcelechat.programs.insurancefraud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_insurance_prices;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.OreBasicType;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialReq;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Ice;
import fr.guiguilechat.tools.PriceCellFactory;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InsuranceFraudController {

	private static final Logger logger = LoggerFactory.getLogger(InsuranceFraudController.class);

	public static class CraftCost {
		public HashMap<String, Double> materials = new HashMap<>();
		public double isks;
	}

	public class InsuranceAnalyzis {

		public final SimpleStringProperty name = new SimpleStringProperty();

		public final SimpleDoubleProperty insuranceGain = new SimpleDoubleProperty();

		public final SimpleDoubleProperty prodTax = new SimpleDoubleProperty();

		public final SimpleDoubleProperty volumeOre = new SimpleDoubleProperty();

		public boolean published = false;

		public int techLevel;

		public int metaLevel;

		public CraftCost craftCost;

		public HashMap<String, Double> requiredOres = new HashMap<>();

		public InsuranceAnalyzis(R_get_insurance_prices prices) {
			Ship item = (Ship) TypeIndex.getType(prices.type_id);
			if (item != null) {
				name.set(item.name);
				published = true;
				techLevel = item.techlevel;
				metaLevel = item.metalevelold;
				craftCost = getCraftRequirement(item.name);
				if (craftCost != null) {
					prodTax.set(craftCost.isks);
					volumeOre.set(minVolumeFor(craftCost.materials.getOrDefault("Tritanium", 0.0),
							craftCost.materials.getOrDefault("Pyerite", 0.0), craftCost.materials.getOrDefault("Isogen", 0.0),
							craftCost.materials.getOrDefault("Mexallon", 0.0), craftCost.materials.getOrDefault("Nocxium", 0.0),
							craftCost.materials.getOrDefault("Zydrine", 0.0), craftCost.materials.getOrDefault("Megacyte", 0.0)));
				}
			} else {
				R_get_universe_types_type_id esiItem = ESIRawPublic.INSTANCE.cache().universe.types(prices.type_id).get();
				if (esiItem != null) {
					name.set(esiItem.name);
					published = esiItem.published;
					if (esiItem.dogma_attributes != null) {
						for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes att : esiItem.dogma_attributes) {
							if (att.attribute_id == TechLevel.INSTANCE.getId()) {
								techLevel = (int) att.value;
							}
							if (att.attribute_id == MetaLevelOld.INSTANCE.getId()) {
								metaLevel = (int) att.value;
							}
						}
					}
				} else {
					name.set("unknown type " + prices.type_id);
					published = false;
				}
			}
			insuranceGain.setValue(prices.levels[5].payout - prices.levels[5].cost);
		}

	}

	@FXML
	private TextField optMatMult;
	// multiplier of material qtty
	protected double matMult = 0.9;

	@FXML
	private TextField optCostMult;
	// multiplier of craft cost. eg no structure, 1% index => 0.01
	protected double costMult = 0.01;

	@FXML
	private TextField optYield;
	// qtty of m³ mined per second
	protected double minerYield = 100;

	@FXML
	private TextField optReproc;
	// qtty of m³ mined per second
	protected double reprocMult = 0.867;

	@FXML
	private Button compute;

	@FXML
	private TableView<InsuranceAnalyzis> table;

	@FXML
	private TableColumn<InsuranceAnalyzis, String> tableNames;

	@FXML
	private TableColumn<InsuranceAnalyzis, Double> tableInsGain;

	@FXML
	private TableColumn<InsuranceAnalyzis, Double> tableProdTax;

	@FXML
	private TableColumn<InsuranceAnalyzis, Double> tableVolOre;

	@FXML
	private TableColumn<InsuranceAnalyzis, Double> tableIskPH;

	@FXML
	private TableColumn<InsuranceAnalyzis, Integer> tableTechLevel;

	@FXML
	private TableColumn<InsuranceAnalyzis, Integer> tableMetaLevel;

	@FXML
	private void initialize() {

		tableNames.setCellValueFactory(e -> e.getValue().name);

		tableInsGain.setCellValueFactory(e -> e.getValue().insuranceGain.asObject());
		tableInsGain.setCellFactory(PriceCellFactory::create);
		tableInsGain.setSortType(SortType.DESCENDING);

		tableProdTax.setCellValueFactory(e -> e.getValue().prodTax.asObject());
		tableProdTax.setCellFactory(PriceCellFactory::create);

		tableVolOre.setCellValueFactory(e -> e.getValue().volumeOre.asObject());
		tableVolOre.setCellFactory(PriceCellFactory::create);

		tableIskPH.setCellValueFactory(e -> hourlygain(e.getValue()).asObject());
		tableIskPH.setCellFactory(PriceCellFactory::create);
		tableIskPH.setSortType(SortType.DESCENDING);

		tableTechLevel.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().techLevel));

		tableMetaLevel.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().metaLevel));

		table.getSortOrder().add(tableIskPH);

		compute.setOnAction(e -> compute());
	}

	public void compute() {
		item2craftQtty.clear();
		matMult = Double.parseDouble(optMatMult.getText());
		costMult = Double.parseDouble(optCostMult.getText());
		minerYield = Double.parseDouble(optYield.getText());
		reprocMult = Double.parseDouble(optReproc.getText());
		table.getItems().clear();
		ESIRawPublic.INSTANCE.cache().insurance.prices().get().parallelStream().map(this::analyze)
		.filter(ana -> ana.published && ana.techLevel == 1).forEachOrdered(table.getItems()::add);
		table.sort();
	}

	protected DoubleBinding hourlygain(InsuranceAnalyzis value) {
		// (insurance gain- production tax)/(volume/yield+3*60)
		return value.insuranceGain.subtract(value.prodTax).multiply(3600.0)
				.divide(value.volumeOre.divide(minerYield).add(180));
	}

	protected InsuranceAnalyzis analyze(R_get_insurance_prices prices) {
		try {
			return new InsuranceAnalyzis(prices);
		} catch (ClassCastException cce) {
			logger.error("insurance price refers to type " + prices.type_id + " which is not a ship");
			return null;
		}
	}

	protected Double[][] reproc = null;

	protected List<Asteroid> asteroids;

	private static final String[] minerals = { "Tritanium", "Pyerite", "Isogen", "Mexallon", "Nocxium", "Zydrine",
	"Megacyte" };

	protected synchronized void makeReprocs() {
		if (reproc != null) {
			return;
		}
		asteroids = Asteroid.METACAT.groups().stream().filter(g -> g != Ice.METAGROUP)
				.flatMap(col -> col.load().values().stream())
				.filter(as -> as.asteroidmetalevel == 1 && getBasicType(as) == as.id).collect(Collectors.toList());
		Double[][] reproc2 = new Double[asteroids.size()][7];
		System.err.println("reprocess values : ");
		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid astero = asteroids.get(i);
			IndustryUsage u = IndustryUsage.load().get(astero.id);
			for (int j = 0; j < minerals.length; j++) {
				reproc2[i][j] = u.reprocessInto.getOrDefault(minerals[j], 0.0) / astero.volume;
			}
			System.err.println(astero.name + java.util.Arrays.asList(reproc2[i]));
		}
		reproc = reproc2;
	}

	public static int getBasicType(Asteroid ore) {
		if (ore.getAttributes().contains(OreBasicType.INSTANCE)) {
			return ore.valueSet(OreBasicType.INSTANCE).intValue();
		}
		return ore.id;
	}

	protected Double[] reproc(String name) {
		for (int i = 0; i < reproc.length; i++) {
			if (asteroids.get(i).name.equals(name)) {
				return reproc[i];
			}
		}
		return null;
	}

	protected double minVolumeFor(double tritanium, double pyerite, double isogen, double mexallon, double nocxium,
			double zydrine, double megacyte) {
		makeReprocs();
		int ret = 0;
		double[] required = new double[] { tritanium, pyerite, isogen, mexallon, nocxium, zydrine, megacyte };
		// use arkonor to get megacyte
		ret += removeFor(required, "Arkonor", 6);
		// use bistot to get zydrine
		ret += removeFor(required, "Bistot", 5);
		// use crockite to get nocxium
		ret += removeFor(required, "Crokite", 4);
		// use Gneiss to get Mexallon
		ret += removeFor(required, "Gneiss", 3);
		// use Dark Ochre to get Isogen
		ret += removeFor(required, "Dark Ochre", 2);
		// use Scordite to get pyerite
		ret += removeFor(required, "Scordite", 1);
		// use Veldspar to get tritanium
		ret += removeFor(required, "Veldspar", 0);
		return ret / reprocMult;
	}

	protected double removeFor(double[] required, String name, int mineralIndex) {
		if (required[mineralIndex] <= 0) {
			return 0;
		}
		Double[] reprocess = reproc(name);
		double volume = required[mineralIndex] / reprocess[mineralIndex];
		for (int i = 0; i < required.length; i++) {
			required[i] -= reprocess[i] * volume;
		}
		System.err.println("require " + volume + " m³ of " + name + " for " + minerals[mineralIndex]);
		return volume;
	}

	private Map<String, CraftCost> item2craftQtty = new HashMap<>();

	// get the lowest materials required to craft given item
	public CraftCost getCraftRequirement(String name) {
		synchronized (item2craftQtty) {
			return getCraftRequirementNoSync(name);
		}
	}

	@SuppressWarnings("rawtypes")
	private CraftCost getCraftRequirementNoSync(String name) {
		if (item2craftQtty.containsKey(name)) {
			return item2craftQtty.get(name);
		}
		CraftCost ret = null;
		IndustryUsage usage = IndustryUsage.load().get(TypeIndex.getTypes(name).get(0).id);
		if (usage != null && usage.productOfManuf.size() != 0) {
			Integer bpID = usage.productOfManuf.iterator().next();
			Blueprint bpo = Blueprint.load().get(bpID);
			if (bpo != null) {
				ret = new CraftCost();
				ArrayList<MaterialReq> requiredMats = bpo.manufacturing.materials;
				if (requiredMats != null) {
					ret.isks = costMult * requiredMats.parallelStream()
							.mapToDouble(
									mat -> mat.quantity * ESIAccess.INSTANCE.markets.getAdjusted(mat.id))
							.sum();
					double produced = bpo.manufacturing.products.get(0).quantity * bpo.manufacturing.products.get(0).probability;
					Map<String, Double> mapMat = requiredMats.stream().collect(Collectors.toMap(req -> req.name(),
							req -> req.quantity == 1 ? req.quantity : Math.ceil(matMult * req.quantity) / produced));
					for (Entry<String, Double> e : mapMat.entrySet()) {
						CraftCost submats = getCraftRequirementNoSync(e.getKey());
						if (submats == null) {
							// the craft mat can't be produced : add it as such
							ret.materials.put(e.getKey(), ret.materials.getOrDefault(e.getKey(), 0.0) + e.getValue());
						} else {
							for (Entry<String, Double> e2 : submats.materials.entrySet()) {
								ret.materials.put(e2.getKey(),
										ret.materials.getOrDefault(e2.getKey(), 0.0) + e2.getValue() * e.getValue());
							}
							ret.isks += submats.isks * e.getValue();
						}
					}
				}
				System.err.println("new craft cost for " + name + " : " + ret.isks);
				for (Entry<String, Double> e : ret.materials.entrySet()) {
					System.err.println(" " + e.getKey() + " *" + e.getValue());
				}
			}
		}
		item2craftQtty.put(name, ret);
		return ret;
	}
}
