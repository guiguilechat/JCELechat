package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.industry.planetary.PlanetaryTaxService;
import fr.guiguilechat.jcelechat.libs.spring.industry.planetary.SchemProductService;
import fr.guiguilechat.jcelechat.libs.spring.industry.planetary.SchematicService;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterialService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLine;
import fr.guiguilechat.jcelechat.libs.spring.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MaterialSourcing;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.ProductValuator;
import fr.guiguilechat.jcelechat.model.formula.market.BrokerFee;
import fr.guiguilechat.jcelechat.model.formula.market.BrokerRelist;
import fr.guiguilechat.jcelechat.model.formula.market.Tax;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.InventoryHtmlController;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.InventoryHtmlController.LinkedMaterial;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary.CuratedP4FromP2;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary.CuratedP4FromP2P1;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.planetary.NLaunchpadsWithSchematics;
import fr.guiguilechat.tools.FormatTools;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlanetEvalService {

	private final BlueprintMaterialService materialService;

	final private PlanetaryTaxService planetaryTaxService;

	final private MarketLineService marketLineService;

	final private SchemProductService schemProductService;

	final private SchematicService schematicService;

	final private TypeService typeService;

	private final @Lazy InventoryHtmlController dogmaHtmlController;

	public static record ConsumeProduct(Map<Type, Long> materials, Map<Type, Long> product) {
	}

	public interface PlanetaryFactory {

		ConsumeProduct production(int hours);

		Collection<Type> products();

		String name();

	}

	public enum PRODUCT_FILTER {

		ANY {
			@Override
			public boolean accept(Collection<Type> products, BlueprintMaterialService materialService) {
				return true;
			}
		},
		P4 {

			@Override
			public boolean accept(Collection<Type> products, BlueprintMaterialService materialService) {
				return products.stream().filter(t -> t.getGroup().getId() == P4_GID).findAny().isPresent();
			}

		},
		P3 {

			@Override
			public boolean accept(Collection<Type> products, BlueprintMaterialService materialService) {
				return products.stream().filter(t -> t.getGroup().getId() == P3_GID).findAny().isPresent();
			}

		},
		P2 {

			@Override
			public boolean accept(Collection<Type> products, BlueprintMaterialService materialService) {
				return products.stream().filter(t -> t.getGroup().getId() == P2_GID).findAny().isPresent();
			}

		},
		USED_MANUF {
			@Override
			public boolean accept(Collection<Type> products, BlueprintMaterialService materialService) {
				return products.stream().filter(p -> p.getGroup().getCategory().getId() == ADVCOMMODITIES_CID)
						.findAny().isPresent();
			}

		},
		;

		public abstract boolean accept(Collection<Type> products, BlueprintMaterialService materialService);

		public boolean accept(PlanetaryFactory factory, BlueprintMaterialService materialService) {
			return accept(factory.products(), materialService);
		}

	}

	@Getter
	@Setter
	@ToString
	public static class PlanetEvalParams {
		private double brpct = (BrokerFee.MINIMUM + 5 * BrokerRelist.MINIMUM) * 100;
		private int customCodeExpertise = 5;
		private double customTaxPct = 5.0;
		private PRODUCT_FILTER filter = PRODUCT_FILTER.ANY;
		private int hours = 2 * 24;
		private boolean hs = false;
		private long location = MarketLineService.JITAIV_ID;
		private double margin = 5.0;
		private MaterialSourcing materialSourcing = MaterialSourcing.BUY_SO_MASS;
		private int nbPlanets = 6;
		private boolean showAll;
		private ProductValuator productValuator = ProductValuator.SELL_BO_MASS;
		private double taxpct = Tax.MINIMUM * 100;
		private double volumicPrice = 1000.0;
	}

	public static final int P4_GID = 1041;
	public static final int P3_GID = 1040;
	public static final int P2_GID = 1034;
	public static final int P1_GID = 1042;
	public static final int P0_CID = 42;
	public static final int ADVCOMMODITIES_CID = 43;

	@Transactional
	public Stream<PlanetaryFactory> streamFactories() {
		return Stream.of(
				CuratedP4FromP2.stream(typeService),
				CuratedP4FromP2P1.stream(typeService),
				schemProductService
						.producers(typeService.byGroupIdIn(List.of(P4_GID, P3_GID, P2_GID))).stream()
						.flatMap(NLaunchpadsWithSchematics::stream))
				.flatMap(s -> s);
	}

	@Getter
	@Setter
	@RequiredArgsConstructor
	public static class FactoryEval {
		private final PlanetaryFactory factory;
		private final PlanetEvalParams params;
		private List<LinkedMaterial> linkedMats;
		private Map<Integer, Long> materialsById;
		private List<LinkedMaterial> linkedProd;
		private Map<Integer, Long> productById;

		double customTax;
		double marginCost;
		double materialCost;
		double totalCost;
		double totalGain;
		double totalSale;
		double volCost;

		@Getter(lazy = true)
		private final String formatedCustomTax = FormatTools.formatPrice(customTax);
		@Getter(lazy = true)
		private final String formatedMarginCost = FormatTools.formatPrice(marginCost);
		@Getter(lazy = true)
		private final String formatedMaterialCost = FormatTools.formatPrice(materialCost);
		@Getter(lazy = true)
		private final String formatedTotalCost = FormatTools.formatPrice(totalCost);
		@Getter(lazy = true)
		private final String formatedTotalGain = FormatTools.formatPrice(totalGain);
		@Getter(lazy = true)
		private final String formatedTotalSale = FormatTools.formatPrice(totalSale);
		@Getter(lazy = true)
		private final String formatedVolCost = FormatTools.formatPrice(volCost);
	}

	@Transactional
	public List<FactoryEval> evaluate(PlanetEvalParams params) {
		schematicService.fetchAll();
		List<FactoryEval> ret = new ArrayList<>(
				streamFactories()
						.filter(f -> params.getFilter().accept(f, materialService))
						.map(pf -> compute(pf, params))
						.toList());
		Set<Integer> allIds = ret.stream()
				.flatMap(fe -> Stream.concat(
						fe.getLinkedMats().stream().map(LinkedMaterial::type),
						fe.getLinkedProd().stream().map(LinkedMaterial::type)))
		    .map(Type::getId)
				.collect(Collectors.toSet());
		Map<Integer, List<MarketLine>> bosByTypeId = marketLineService.locationBos(params.getLocation(), allIds);
		Map<Integer, List<MarketLine>> sosByTypeId = marketLineService.locationSos(params.getLocation(), allIds);
		Map<Integer, Double> exportTaxById = planetaryTaxService.exportTaxById();
		Map<Integer, Double> importTaxById = planetaryTaxService.importTaxById();
		double taxMult = 0.01 * params.customTaxPct
				+ planetaryTaxService.concordTaxMult(params.isHs(), params.getCustomCodeExpertise());
		ret.stream().forEach(fe -> {
			double matCost = params.getMaterialSourcing().cost(fe.getMaterialsById(), params.getTaxpct(), params.getBrpct(),
					bosByTypeId, sosByTypeId);
			double prodSale = params.getProductValuator().value(fe.getProductById(), params.getTaxpct(), params.getBrpct(),
					bosByTypeId, sosByTypeId);
			double marginCost = params.getMargin() * prodSale / 100;

			double volCost = 0.0;
			double customTax = 0.0;
			for (Entry<Integer, Long> e : fe.getProductById().entrySet()) {
				long qtty = e.getValue();
				Type t = typeService.byId(e.getKey());
				volCost += params.getProductValuator().haulingCost(qtty,
						params.getVolumicPrice() * t.getVolume().doubleValue());
				customTax += taxMult * exportTaxById.get(t.getId()) * qtty;
			}
			for (Entry<Integer, Long> e : fe.getMaterialsById().entrySet()) {
				long qtty = e.getValue();
				Type t = typeService.byId(e.getKey());
				volCost += params.getMaterialSourcing().haulingCost(qtty,
						params.getVolumicPrice() * t.getVolume().doubleValue());
				customTax += taxMult * importTaxById.get(t.getId()) * qtty;
			}

			double totalCost = matCost + marginCost + volCost + customTax;
			fe.setCustomTax(customTax);
			fe.setMarginCost(marginCost);
			fe.setMaterialCost(matCost);
			fe.setTotalCost(totalCost);
			fe.setTotalGain(prodSale - totalCost);
			fe.setTotalSale(prodSale);
			fe.setVolCost(volCost);

		});
		ret.sort(Comparator.comparing(fe -> -fe.getTotalGain()));
		if (!params.isShowAll()) {
			ret = new ArrayList<>(ret.stream()
					.collect(Collectors.groupingBy(eval -> eval.getLinkedProd().get(0).type()))
					.values().stream()
					.map(l -> l.get(0))
					.toList());
			ret.sort(Comparator.comparing(fe -> -fe.getTotalGain()));
		}
		return ret;
	}

	private FactoryEval compute(PlanetaryFactory pf, PlanetEvalParams params) {
		FactoryEval ret = new FactoryEval(pf, params);
		ConsumeProduct prod = pf.production(params.getHours());
		ret.setLinkedMats(prod.materials().entrySet().stream()
		    .sorted(Comparator.comparing(e -> e.getKey().name()))
				.map(e -> dogmaHtmlController.linkedMaterial(e.getKey(), e.getValue().intValue() * params.getNbPlanets()))
				.toList());
		ret.setMaterialsById(
				prod.materials().entrySet().stream()
		        .collect(Collectors.toMap(e -> e.getKey().getId(), e -> e.getValue() * params.getNbPlanets())));
		ret.setLinkedProd(prod.product().entrySet().stream()
		    .sorted(Comparator.comparing(e -> e.getKey().name()))
				.map(e -> dogmaHtmlController.linkedMaterial(e.getKey(), e.getValue().intValue() * params.getNbPlanets()))
				.toList());
		ret.setProductById(
				prod.product.entrySet().stream()
		        .collect(Collectors.toMap(e -> e.getKey().getId(), e -> e.getValue() * params.getNbPlanets())));
		return ret;
	}

}
