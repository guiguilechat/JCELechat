package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Limit;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.CategoryService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup.MarketGroup;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup.MarketGroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.RegionService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.StationService;
import fr.guiguilechat.jcelechat.libs.spring.trade.AggregatedTypeHistory;
import fr.guiguilechat.jcelechat.libs.spring.trade.ContractMarketAggregator;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeBpc;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeBpo;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractFacadeNonBp;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService.ContractTypeVariant;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryLineService;
import fr.guiguilechat.jcelechat.libs.spring.trade.marketranking.MarketRankingRepository.RankedOffer;
import fr.guiguilechat.jcelechat.libs.spring.trade.marketranking.MarketRankingService;
import fr.guiguilechat.jcelechat.libs.spring.trade.marketranking.MarketRankingService.BoSoChoice;
import fr.guiguilechat.jcelechat.libs.spring.trade.marketranking.MarketRankingService.GroupCategoryChoice;
import fr.guiguilechat.jcelechat.libs.spring.trade.tools.MarketOrder;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.InventoryHtmlController.LinkedType;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.MarketHistoryRestController;
import fr.guiguilechat.tools.FormatTools;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/html/market")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketHtmlController {

	private final CategoryService categoryService;

	@Lazy
	private final ContractEvalController contractEvalController;

	private final ContractFacadeBpc contractFacadeBpc;

	private final ContractFacadeBpo contractFacadeBpo;

	private final ContractFacadeNonBp contractFacadeNonBp;

	private final ContractInfoService contractInfoService;

	private final ContractMarketAggregator contractMarketAggregator;

	private final GroupService groupService;

	@Lazy
	private final InventoryHtmlController dogmaHtmlController;

	@Lazy
	private final HistoryLineService historyLineService;

	@Lazy
	private final MarketHistoryRestController historyRestController;

	@Lazy
	private final InventoryHtmlController inventoryHtmlController;

	private final MarketGroupService marketGroupService;

	private final MarketRankingService marketRankingService;

	private final RegionService regionService;

	private final StationService stationService;

	private final TypeService typeService;

	@Transactional
	@GetMapping("/{typeId}")
	public String getTypeMarket(Model model, @PathVariable int typeId,
			Optional<Integer> me,
			Optional<Integer> te,
			Optional<Boolean> copy) {
		Type type = typeService.byId(typeId);
		int meValue = me == null ? 0 : me.orElse(0);
		int teValue = te == null ? 0 : te.orElse(0);
		boolean copyValue = copy == null ? false : copy.orElse(false);
		Map<Integer, String> regionNamesById = regionService.namesById();
		String name = null;
		if (type != null) {
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

			log.trace("fetching contract facade sos");
			List<MarketOrder> sos = contractFacadeBpc.sos(typeId, meValue, teValue).stream()
					.peek(mo -> mo.resolveRegionName(regionNamesById))
					.toList();
			// System.err.println("found " + sos.size() + " orders for " + name);
			model.addAttribute("sos", sos);

			List<MarketOrder> completed = contractFacadeBpc.streamSold(typeId, meValue, teValue, Limit.of(100))
					.peek(mo -> mo.resolveRegionName(regionNamesById))
					.toList();
			model.addAttribute("completed", completed);
		} else // working wit non-copy
		if (meValue < 1 && teValue < 1) {
			// working with base type

			model.addAttribute("sos",
					contractMarketAggregator.sellOrders(typeId).stream()
							.peek(mo -> mo.resolveRegionName(regionNamesById)).toList());
			model.addAttribute("bos",
					contractMarketAggregator.buyOrders(typeId).stream()
							.peek(mo -> mo.resolveRegionName(regionNamesById))
							.toList());
		} else {
			// working with researched BPO
			model.addAttribute("showDetails", true);
			List<MarketOrder> sos = contractFacadeBpo.sos(typeId, meValue, teValue).stream()
					.peek(mo -> mo.resolveRegionName(regionNamesById))
					.peek(mo -> mo.setUrl(contractEvalController.uri(mo.getContractId()).toString()))
					.toList();
			// System.err.println("found " + sos.size() + " orders for " + name);
			model.addAttribute("sos", sos);
		}
		if (meValue > 0 || teValue > 0 || copyValue) {
			model.addAttribute("baseTypeUrl", uri(typeId).toString());
			model.addAttribute("historyChartUrl",
					historyRestController.uri(typeId, copyValue, meValue, teValue).toString());
		}
		List<ContractTypeVariant> variants = contractInfoService.variants(typeId);
		// System.err.println("received variants " + variants);
		if (!variants.isEmpty()) {
			List<LinkedMarketType> bpoVariants = variants.stream()
					.filter(v -> !v.copy())
					.sorted(Comparator.comparing(
							(Function<? super ContractTypeVariant, ? extends Integer>) ContractTypeVariant::meteval))
					.map(ctv -> linkedMarketType(type, ctv))
					.toList();
			if (!bpoVariants.isEmpty()) {
				model.addAttribute("bpoVariants", bpoVariants);
			}
			List<LinkedMarketType> bpcVariants = variants.stream()
					.filter(ContractTypeVariant::copy)
					.sorted(Comparator.comparing(
							(Function<? super ContractTypeVariant, ? extends Integer>) ContractTypeVariant::meteval))
					.map(ctv -> linkedMarketType(type, ctv))
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
		return new LinkedMarketType(name + (copy ? "(cp)" : "") + "  " + me + "/" + te,
				uri(type, me, te, copy).toString());
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
	public enum PERIOD {
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
						types.stream().map(this::linkedMarketType).sorted(Comparator.comparing(lmt -> lmt.name))
								.toList());
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
		MarketGroup marketGroup = marketGroupService.byId(marketGroupId);
		if (marketGroup != null) {
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

	//

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
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getMarketGroup", null, "" + marketGroup.getId())
				.build()
				.toUri();
	}

	public static record LinkedMarketGroup(String name, String url) {
	}

	public LinkedMarketGroup linkedMarketGroup(MarketGroup marketGroup) {
		return new LinkedMarketGroup(marketGroup.name(), uri(marketGroup).toString());
	}

	// group ranking

	@Transactional
	@GetMapping("ranking/{filter}/{filterId}")
	public String getRanking(Model model,
			@PathVariable GroupCategoryChoice filter,
			@PathVariable int filterId,
			@RequestParam List<Long> locationIds)
			throws InterruptedException, ExecutionException {
		if (filter == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"requires filter, received " + filter);
		}
		switch (filter) {
		case CATEGORY:
			Category cat = categoryService.byId(filterId);
			if (cat == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"filter id " + filterId + " can't be resolved to a category");
			}
			model.addAttribute("filterName", "category " + cat.getName());
			model.addAttribute("rankBO",
					linkRankings(marketRankingService.rankCategoryOffers(locationIds, filterId, BoSoChoice.BO).get()));
			model.addAttribute("rankSO",
					linkRankings(marketRankingService.rankCategoryOffers(locationIds, filterId, BoSoChoice.SO).get()));
			break;
		case GROUP:
			Group group = groupService.byId(filterId);
			if (group == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"filter id " + filterId + " can't be resolved to a group");
			}
			model.addAttribute("filterName", "group " + group.getName());
			model.addAttribute("rankBO",
					linkRankings(marketRankingService.rankGroupOffers(locationIds, filterId, BoSoChoice.BO).get()));
			model.addAttribute("rankSO",
					linkRankings(marketRankingService.rankGroupOffers(locationIds, filterId, BoSoChoice.SO).get()));
			break;
		default:
			throw new UnsupportedOperationException("case " + filter + " not handled");
		}

		model.addAttribute("filter", filterId);
		model.addAttribute("locationIds", locationIds);
		String locationName = "" + locationIds.size() + " locations";
		if (locationIds.size() == 1) {
			long locationId = locationIds.get(0);
			Station sta = locationId > Integer.MAX_VALUE
					? null
					: stationService.byId((int) locationId);
			locationName = sta == null ? "location:" + locationId : sta.name();
		}
		model.addAttribute("locationName", locationName);
		model.addAttribute("amarrUrl", rankingURI(filter, filterId, List.of(Station.AMARR_HUB_ID)).toString());
		model.addAttribute("dodixieUrl", rankingURI(filter, filterId, List.of(Station.DODIXIE_HUB_ID)).toString());
		model.addAttribute("hekUrl", rankingURI(filter, filterId, List.of(Station.HEK_HUB_ID)).toString());
		model.addAttribute("jitaUrl", rankingURI(filter, filterId, List.of(Station.JITA_HUB_ID)).toString());
		model.addAttribute("rensUrl", rankingURI(filter, filterId, List.of(Station.RENS_HUB_ID)).toString());
		model.addAttribute("hsHubsUrl", rankingURI(filter, filterId, Station.HS_HUB_IDs).toString());
		return "market/ranking";
	}

	public static record LinkedRanking(Object filter, String url) {
		@Override
		public final String toString() {
			return filter.toString();
		}
	}

	public URI rankingURI(
			GroupCategoryChoice filterType,
			int filterId,
			Iterable<Long> locationIds) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "getRanking", null,
						filterType,
						filterId,
						locationIds)
				.build()
				.toUri();
	}

	protected URI rankingURI(Group group, Iterable<Long> locationIds) {
		return rankingURI(GroupCategoryChoice.GROUP, group.getId(), locationIds);
	}

	public LinkedRanking linkedRanking(Group group, Iterable<Long> locationIds) {
		return new LinkedRanking(group, rankingURI(group, locationIds).toString());
	}

	protected URI rankingURI(Category category, Iterable<Long> locationIds) {
		return rankingURI(GroupCategoryChoice.CATEGORY, category.getId(), locationIds);
	}

	public LinkedRanking linkedRanking(Category category, Iterable<Long> locationIds) {
		return new LinkedRanking(category, rankingURI(category, locationIds).toString());
	}

	public static record LinkedTypeRanking(LinkedType type, String formattedPrice, String formattedRank) {

	}

	public List<LinkedTypeRanking> linkRankings(List<RankedOffer> rankings) {
		Map<Integer, Type> id2type = typeService.byId(rankings.stream().map(RankedOffer::type_id).toList()).stream()
				.collect(Collectors.toMap(Type::getId, o -> o));
		return rankings.stream()
				.map(r -> new LinkedTypeRanking(
						inventoryHtmlController.linkedType(id2type.get(r.type_id())),
						FormatTools.formatPrice(r.price().doubleValue()),
						"" + r.rank()))
				.toList();
	}

}
