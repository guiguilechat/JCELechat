package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.contract.info")
@Order(6) // depends on type and contractregion for the items ; then set to a higher
// number because it's likely to create more errors
public class ContractInfoService extends RemoteEntityService<
ContractInfo,
Integer,
R_get_contracts_public_items_contract_id[],
ContractInfoRepository> {

	//
	// implementation
	//

	@Lazy
	private final ContractItemService contractItemService;

	@Lazy
	private final TypeService typeService;


	@Override
	protected Requested<R_get_contracts_public_items_contract_id[]> fetchData(Integer id,
			Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.requestGetPages(
				(page, props) -> ESIRawPublic.INSTANCE.get_contracts_public_items(id, page, props), properties)
				.mapBody(l -> l.toArray(R_get_contracts_public_items_contract_id[]::new));
	}

	@Override
	protected ContractInfo create(Integer entityId) {
		ContractInfo ret = new ContractInfo();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected void preSave(ContractInfo data) {
		super.preSave(data);
		data.updateStatus();
	}

	@Override
	protected void updateResponseOk(Map<ContractInfo, R_get_contracts_public_items_contract_id[]> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, Type> idToType = typeService.createIfAbsent(responseOk.values().stream()
				.flatMap(Stream::of)
				.map(c -> c.type_id)
				.distinct().toList());
		List<ContractItem> items = new ArrayList<>();
		for (Entry<ContractInfo, R_get_contracts_public_items_contract_id[]> e : responseOk.entrySet()) {
			ContractInfo ci = e.getKey();
			R_get_contracts_public_items_contract_id[] arr = e.getValue();
			for (R_get_contracts_public_items_contract_id contractData : arr) {
				items.add(ContractItem.of(ci, idToType.get(contractData.type_id), contractData));
			}
			ci.setFetchActive(false);
		}
		contractItemService.saveAll(items);
		saveAll(responseOk.keySet());
	}

	/**
	 * 403 actually means the contract still exists but can't be accepted, so either
	 * already accepted, or expired
	 */
	@Override
	protected void update403(ContractInfo data, Requested<R_get_contracts_public_items_contract_id[]> response) {
		boolean accepted = response.getError().contains("accepted");
		data.setCanceled(false);
		data.setCompleted(accepted);
		data.setExpired(!accepted);
		data.setFetchActive(false);
		data.setRemoved(true);
	}

	@Override
	protected void update404(ContractInfo data, Requested<R_get_contracts_public_items_contract_id[]> response) {
		data.setCanceled(true);
		data.setCompleted(false);
		data.setExpired(false);
		data.setFetchActive(false);
		data.setRemoved(true);
	}

	@Override
	protected void updateNullBody(ContractInfo data, Requested<R_get_contracts_public_items_contract_id[]> response) {
		// do nothing, it means there is no item in the contract.
		updateMetaOk(data, response);
	}

	/**
	 * only fetch the contracts which are not removed first, then fetch the status
	 * of contracts that are removed.
	 */
	@Override
	protected List<ContractInfo> listToUpdate() {
		int batchSize = maxAllowedQueries();
		if (batchSize < 1) {
			return List.of();
		}
		List<ContractInfo> ret = repo().findByFetchActiveTrueAndRemovedFalseAndExpiresLessThanOrderByExpiresAsc(
				Instant.now(),
				Limit.of(batchSize));
		if (ret.isEmpty()) {
			// no new contract : fetch the old contract that have been removed from the list
			batchSize = pessimisticBatchSize(batchSize);
			if (batchSize <= 0) {
				return List.of();
			}
			ret = repo().findByFetchActiveTrueAndExpiresBeforeOrderByExpiresAsc(
					Instant.now(),
					Limit.of(batchSize));
			if (!ret.isEmpty()) {
				log.debug("will fail {} contrat info requests to deduce their end state", ret.size());
			}
		} else {
			ContractInfo firstCI = ret.get(0);
			// if the first contract is older than a day, it's likely to fail : only fetch
			// with pessimistic batch size, so half the remaining esi errors
			if (ChronoUnit.DAYS.between(firstCI.getDateIssued(), Instant.now()) >= 1) {
				int pessimisticBatchSize = pessimisticBatchSize(batchSize);
				log.debug("pessimistic batch sized from {} to {} with first contract issued date {}",
				    batchSize,
						pessimisticBatchSize,
						firstCI.getDateIssued());
				if (pessimisticBatchSize <= 0) {
					return List.of();
				}
				if (pessimisticBatchSize < ret.size()) {
					ret = ret.subList(0, pessimisticBatchSize);
				}
			}
		}
		return ret;
	}

	protected int pessimisticBatchSize(int optimisticBatchSize) {
		int maxErrors = globalErrors().availErrors();
		if (maxErrors < 20) {
			return 0;
		}
		return Math.min(optimisticBatchSize, maxErrors / 2);
	}

	//
	// force update analyzis
	//

	private Integer lastUpdateEnd = null;

	private int updateAnalyzisBatchSize = 10000;

	@Override
	protected void preUpdate() {
		super.preUpdate();
		// re analyze contracts if needed
		// ids are fetched first, then contracts with items are fetched. Two queries
		// needed otherwise the fetch join generates too much data and the one query is
		// too slow
		if (lastUpdateEnd != null) {
			long preListIdsTime = System.currentTimeMillis();
			List<Integer> ids = repo().listIdsByFetchedTrue(lastUpdateEnd, updateAnalyzisBatchSize);
			long postListIdsTime = System.currentTimeMillis();
			if (ids.isEmpty()) {
				lastUpdateEnd = null;
				log.trace(" no more contract to re analyze");
			} else {
				List<ContractInfo> list = repo().findByIdIn(ids);
				long postFetchTime = System.currentTimeMillis();
				int maxId = list.get(list.size() - 1).getId();
				log.debug("re analyzing {} contract infos, last id={} max id={}", list.size(), lastUpdateEnd, maxId);
				for (ContractInfo ci : list) {
					ci.reAnalize();
				}
				saveAll(list);
				long postAnalyze = System.currentTimeMillis();
				log.trace(" finished analyzing {} contracts in {} ms (listIds={} fetch={} analyze+save={})", list.size(),
						postAnalyze - preListIdsTime,
						postListIdsTime - preListIdsTime,
						postFetchTime - postListIdsTime,
						postAnalyze - postFetchTime);
				lastUpdateEnd = maxId;
			}
		}
	}

	public void requestAnalize() {
		lastUpdateEnd = 0;
	}

	//
	// usage
	//

	/**
	 * @param region a contract region
	 * @return list of contracts in given region that were still available at the
	 *           last update of that region's contract, and for which we already
	 *           have fetched the data, with the key being the contract id
	 */
	public Map<Integer, ContractInfo> presentFetchedByRegion(int regionId) {
		return repo().findByRegionIdAndRemovedFalseAndFetchedTrue(regionId).stream()
				.collect(Collectors.toMap(ContractInfo::getId, c -> c));
	}

	public Map<ContractRegion, Map<Integer, ContractInfo>> presentByRegion(Iterable<ContractRegion> regions) {
		return repo().findByRegionInAndRemovedFalse(regions).stream()
				.collect(Collectors.groupingBy(ContractInfo::getRegion, Collectors.toMap(ContractInfo::getId, c -> c)));
	}

	/**
	 * @return a stream of the contracts that provides at least an item of given
	 *           type and do not require an item
	 */
	public Stream<ContractInfo> exchangesSelling() {
		return repo().findByTypeAndFetchedTrueAndRemovedFalseAndOffersNonBpcTrueAndRequestsItemFalse(
				get_contracts_public_region_id_type.item_exchange);
	}

	/**
	 * @return a stream of the contracts that require at least an item and do not
	 *           offer an item
	 */
	public Stream<ContractInfo> exchangesBuying() {
		return repo().findByTypeAndFetchedTrueAndRemovedFalseAndRequestsItemTrueAndOffersItemFalse(
				get_contracts_public_region_id_type.item_exchange);
	}

	public static record ContractTypeVariant(int typeId, int me, int te, boolean copy){

		public ContractTypeVariant(int typeId, Object[] arr) {
			this(typeId, ((Number) arr[0]).intValue(), ((Number) arr[1]).intValue(), (Boolean) arr[2]);
		}

		/**
		 * @return an int to compare them using (me+te) then me, so that it sorts first
		 *           ones as
		 *           (0,0),(0,1),(1,0),(0,2),(1,1),(2,0) etc.
		 */
		public int meteval() {
			return me * 100 + te * 101;
		}

	}


	public List<ContractTypeVariant> variants(int typeId) {
		return repo().listTypeVariants(typeId).stream().map(arr -> new ContractTypeVariant(typeId, arr)).toList();
	}


	//
	// specifications to help criterions
	//

	public static Specification<ContractInfo> valid() {
		return (ci, cq, cb) -> cb.and(cb.isTrue(ci.get("fetched")), cb.isFalse(ci.get("removed")));
	}

	public static Specification<ContractInfo> forType(get_contracts_public_region_id_type type) {
		return (ci, cq, cb) -> cb.equal(ci.get("type"), type);
	}

	public static Specification<ContractInfo> isHs() {
		return (ci, cq, cb) -> cb.isTrue(ci.get("secHigh"));
	}

	public static Specification<ContractInfo> isLs() {
		return (ci, cq, cb) -> cb.isTrue(ci.get("secLow"));
	}

	public static Specification<ContractInfo> isNs() {
		return (ci, cq, cb) -> cb.isTrue(ci.get("secNull"));
	}

	public static Specification<ContractInfo> inRegion(Region region) {
		return (ci, cq, cb) -> cb.equal(ci.get("regionId"), region.getId());
	}

	public static Specification<ContractInfo> requestsItem(boolean requests) {
		return (ci, cq, cb) -> cb.equal(ci.get("requestsItem"), requests);
	}

	public static Specification<ContractInfo> offersItem(boolean offers) {
		return (ci, cq, cb) -> cb.equal(ci.get("offersItem"), offers);
	}

	public static Specification<ContractInfo> offersNonBpc(boolean offers) {
		return (ci, cq, cb) -> cb.equal(ci.get("offersNonBpc"), offers);
	}

	//
	// cache management
	//

	/**
	 * notified when one+ item of a contract is modified
	 */
	public interface ContractItemsListener extends EntityUpdateListener {
	}

	@Getter
	@Lazy
	private final Optional<List<ContractItemsListener>> listeners;

}
