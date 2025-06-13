package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.items.marketgroup.MarketGroup;
import fr.guiguilechat.jcelechat.libs.spring.items.marketgroup.MarketGroupService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.trade.AggregatedTypeHistory;
import fr.guiguilechat.jcelechat.libs.spring.trade.ContractMarketAggregator;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeBpc;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeBpo;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeNonBp;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService.ContractTypeVariant;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.StationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.InventoryHtmlController.LinkedType;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.MarketHistoryRestController;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/html/market")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketHtmlController {

	@Lazy
	private final ContractEvalController contractEvalController;

	private final ContractFacadeBpc contractFacadeBpc;

	private final ContractFacadeBpo contractFacadeBpo;

	private final ContractFacadeNonBp contractFacadeNonBp;

	private final ContractInfoService contractInfoService;

	@Lazy
	private final InventoryHtmlController dogmaHtmlController;

	@Lazy
	private final HistoryLineService historyLineService;

	@Lazy
	private final MarketHistoryRestController historyRestController;

	private final MarketGroupService marketGroupService;

	private final ContractMarketAggregator contractMarketAggregator;

	private final RegionService regionService;

	private final StationService stationService;

	private final TypeService typeService;

	@Transactional
	@GetMapping("/{typeId}")
	public String getTypeMarket(Model model, @PathVariable int typeId,
			Optional<Integer> me,
			Optional<Integer> te,
			Optional<Boolean> copy) {
		Optional<Type> oType = typeService.findById(typeId);
		int meValue = me == null ? 0 : me.orElse(0);
		int teValue = te == null ? 0 : te.orElse(0);
		boolean copyValue = copy == null ? false : copy.orElse(false);
		Map<Integer, String> regionNamesById = regionService.namesById();
		Map<Integer, String> stationNamesById = stationService.namesById();
		Map<Long, String> structuresNamesById = Map.of();
		String name = null;
		if (oType.isPresent()) {
			Type type = oType.get();
			model.addAttribute("typeUrl", dogmaHtmlController.uri(type).toString());
			name = type.name();
		} else {
			name = "unknown" + typeId;
		}
		if (meValue > 0 || teValue > 0 || copyValue) {
			name = name + (copyValue ? "(CP)" : "") + " " + meValue + "/" + teValue;
		}
		model.addAttribute("name", name);
		if (copyValue) {
			// working with BPC
			model.addAttribute("showDetails", true);

			List<MarketOrder> sos = contractFacadeBpc.streamSOs(typeId, meValue, teValue)
					.peek(mo -> mo.resolveRegionName(regionNamesById).resolveLocationName(stationNamesById, structuresNamesById))
					.toList();
			// System.err.println("found " + sos.size() + " orders for " + name);
			model.addAttribute("sos", sos);

			List<MarketOrder> completed = contractFacadeBpc.streamSold(typeId, meValue, teValue, Limit.of(100))
					.peek(mo -> mo.resolveRegionName(regionNamesById).resolveLocationName(stationNamesById, structuresNamesById))
					.toList();
			model.addAttribute("completed", completed);
		} else // working wit non-copy
		if (meValue < 1 && teValue < 1) {
			// working with base type
			model.addAttribute("sos",
					contractMarketAggregator.sellOrders(typeId).stream()
					.peek(
							mo -> mo.resolveRegionName(regionNamesById).resolveLocationName(stationNamesById,
									structuresNamesById))
					.toList());
			model.addAttribute("bos",
					contractMarketAggregator.buyOrders(typeId).stream()
					.peek(
							mo -> mo.resolveRegionName(regionNamesById).resolveLocationName(stationNamesById,
									structuresNamesById))
					.toList());
		} else {
			// working with researched BPO
			model.addAttribute("showDetails", true);
			List<MarketOrder> sos = contractFacadeBpo.streamSOs(typeId, meValue, teValue)
					.peek(
							mo -> mo.resolveRegionName(regionNamesById).resolveLocationName(stationNamesById, structuresNamesById))
					.peek(mo -> mo.setUrl(contractEvalController.uri(mo.getContractId()).toString()))
					.toList();
			// System.err.println("found " + sos.size() + " orders for " + name);
			model.addAttribute("sos", sos);
		}
		if (meValue > 0 || teValue > 0 || copyValue) {
			model.addAttribute("baseTypeUrl", uri(typeId).toString());
			model.addAttribute("historyChartUrl", historyRestController.uri(typeId, copyValue, meValue, teValue).toString());
		}
		List<ContractTypeVariant> variants = contractInfoService.variants(typeId);
		// System.err.println("received variants " + variants);
		if (!variants.isEmpty()) {
			List<LinkedMarketType> bpoVariants = variants.stream()
					.filter(v -> !v.copy())
					.sorted(Comparator.comparing((Function<? super ContractTypeVariant, ? extends Integer>) ContractTypeVariant::meteval))
					.map(ctv -> linkedMarketType(oType.orElse(null), ctv))
					.toList();
			if (!bpoVariants.isEmpty()) {
				model.addAttribute("bpoVariants", bpoVariants);
			}
			List<LinkedMarketType> bpcVariants = variants.stream()
					.filter(ContractTypeVariant::copy)
					.sorted(Comparator.comparing((Function<? super ContractTypeVariant, ? extends Integer>) ContractTypeVariant::meteval))
					.map(ctv -> linkedMarketType(oType.orElse(null), ctv))
					.toList();
			if (!bpcVariants.isEmpty()) {
				model.addAttribute("bpcVariants", bpcVariants);
			}
		}
		return "market/type";
	}

	public URI uri(int typeId) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "getTypeMarket", null, "" + typeId, null, null, null).build()
				.toUri();
	}

	public URI uri(Type type) {
		return uri(type.getId());
	}

	public URI uri(int typeId, int me, int te, boolean copy) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "getTypeMarket", null, "" + typeId, me, te, copy).build()
				.toUri();
	}

	public URI uri(Type type, int me, int te, boolean copy) {
		return uri(type.getId(), me, te, copy);
	}

	public static record LinkedMarketType(String name, String url) {
	}

	public LinkedMarketType linkedMarketType(Type type) {
		return new LinkedMarketType(type.name(), uri(type).toString());
	}

	public LinkedMarketType linkedMarketType(Type type, int me, int te, boolean copy) {
		String name = type == null ? "null" : type.name();
		return new LinkedMarketType(name + (copy ? "(cp)" : "") + "  " + me + "/" + te, uri(type, me, te, copy).toString());
	}

	public LinkedMarketType linkedMarketType(Type type, ContractTypeVariant ctv) {
		return linkedMarketType(type, ctv.me(), ctv.te(), ctv.copy());
	}

	@GetMapping("")
	public String getRoot() {
		return "redirect:market/search";
	}

	@RequiredArgsConstructor
	@Getter
	public enum PERIOD{
		week(7), month(30), year(365);

		private final int days;
	}

	@Transactional
	@GetMapping("/search")
	public String getSearch(Model model, Optional<String> typeName, Optional<PERIOD> period, Optional<Integer> limit) {
		if (typeName.isPresent() && !typeName.get().isBlank()) {
			List<Type> types = typeService.search(typeName.get());
			if (types.size() == 1) {
				return "redirect:" + types.get(0).getId();
			} else {
				model.addAttribute("types",
						types.stream().map(this::linkedMarketType).sorted(Comparator.comparing(lmt -> lmt.name)).toList());
			}
		}
		PERIOD periodValue = period.orElse(PERIOD.week);
		model.addAttribute("periods", PERIOD.values());
		model.addAttribute("period", periodValue);
		int limitValue = limit.orElse(100);

		List<AggregatedTypeHistory> regionalSales = historyLineService.aggregateHighestIskVolume(periodValue.getDays(),
				limitValue);
		for (AggregatedTypeHistory line : regionalSales) {
			line.setUrl(uri(line.getTypeId()).toString());
		}
		model.addAttribute("regionalMarketSales", regionalSales);

		List<AggregatedTypeHistory> unresearchedContractSales = contractFacadeNonBp.aggregateHighestIskVolume(
				periodValue.getDays(),
				limitValue);
		for (AggregatedTypeHistory line : unresearchedContractSales) {
			line.setUrl(uri(line.getTypeId()).toString());
		}
		model.addAttribute("unresearchedContractSales", unresearchedContractSales);

		List<AggregatedTypeHistory> bpoContractSales = contractFacadeBpo.aggregateHighestIskVolume(
				periodValue.getDays(),
				limitValue);
		for (AggregatedTypeHistory line : bpoContractSales) {
			line.setUrl(uri(line.getTypeId(), line.getMe(), line.getTe(), false).toString());
		}
		model.addAttribute("bpoContractSales", bpoContractSales);

		List<AggregatedTypeHistory> bpcContractSales = contractFacadeBpc.aggregateHighestIskVolume(
				periodValue.getDays(),
				limitValue);
		for (AggregatedTypeHistory line : bpcContractSales) {
			line.setUrl(uri(line.getTypeId(), line.getMe(), line.getTe(), true).toString());
		}
		model.addAttribute("bpcContractSales", bpcContractSales);

		// TODO add period most sold for contracts (unresearched, BPO, BPC)
		return "market/index";
	}

	@Transactional
	@GetMapping("/search/")
	public String getSearchSlash(Model model, Optional<String> typeName) {
		return getSearch(model, typeName, null, null);
	}

	// market groups

	@Transactional
	@GetMapping("/group/{marketGroupId}")
	public String getMarketGroup(Model model, @PathVariable int marketGroupId) {
		Optional<MarketGroup> oMarketGroup = marketGroupService.findById(marketGroupId);
		if (oMarketGroup.isPresent()) {
			MarketGroup marketGroup = oMarketGroup.get();
			model.addAttribute("name", marketGroup.name());
			if (marketGroup.getParent() != null) {
				model.addAttribute("parent", linkedMarketGroup(marketGroup.getParent()));
			}
			if (marketGroup.getSubGroups() != null && !marketGroup.getSubGroups().isEmpty()) {
				model.addAttribute("children", marketGroup.getSubGroups().stream()
						.map(this::linkedMarketGroup)
						.sorted(Comparator.comparing(l -> l.name))
						.toList());
			}
			if (marketGroup.getTypes() != null && !marketGroup.getTypes().isEmpty()) {
				model.addAttribute("types", marketGroup.getTypes().stream()
						.map(dogmaHtmlController::linkedType)
						.sorted(Comparator.comparing(LinkedType::name))
						.toList());
			}
		} else {
			model.addAttribute("name", "unknown" + marketGroupId);
		}
		return "market/group";
	}

	@Transactional
	@GetMapping("/groups")
	public String getMarketGroups(Model model) {
		model.addAttribute("roots",
				marketGroupService.roots().stream()
				.sorted(Comparator.comparing(MarketGroup::name))
				.map(this::linkedMarketGroup)
				.toList());
		return "market/groups";
	}

	public URI uri(MarketGroup marketGroup) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getMarketGroup", null, "" + marketGroup.getId()).build()
				.toUri();
	}

	public static record LinkedMarketGroup(String name, String url) {
	}

	public LinkedMarketGroup linkedMarketGroup(MarketGroup marketGroup) {
		return new LinkedMarketGroup(marketGroup.name(), uri(marketGroup).toString());
	}

}
