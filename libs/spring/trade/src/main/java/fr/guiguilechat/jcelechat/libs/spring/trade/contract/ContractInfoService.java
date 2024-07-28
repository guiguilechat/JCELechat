package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
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
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.contract.info")
@Order(10) // depends on type for the items ; then set to a higher number because it's
           // likely to create more errors
public class ContractInfoService extends ARemoteEntityService<
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

	@Getter
	@Setter
	/**
	 * number of fetch we accept to do, at max, when we have error contracts (those
	 * removed we want to know if they were completed or not)
	 */
	private int maxExpectedErrors = 50;

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
	
	@Override
	protected void update403(ContractInfo data, Requested<R_get_contracts_public_items_contract_id[]> response) {
		data.setRemoved(true);
		data.setCompleted(true);
		data.setFetchActive(false);
	}

	@Override
	protected void update404(ContractInfo data, Requested<R_get_contracts_public_items_contract_id[]> response) {
		data.setRemoved(true);
		data.setCanceled(true);
		data.setFetchActive(false);
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
		lastBatchSize = nextBatchSize();
		if (lastBatchSize < 1) {
			return List.of();
		}
		List<ContractInfo> ret = repo().findByFetchActiveTrueAndRemovedFalseAndExpiresLessThanOrderByExpiresAsc(
		    Instant.now(), Limit.of(lastBatchSize));
		if (ret.isEmpty()) {
			ret = repo().findByFetchActiveTrueAndExpiresLessThanOrderByExpiresAsc(Instant.now(),
			    Limit.of(Math.min(lastBatchSize / 2, maxExpectedErrors)));
		}
		return ret;
	}

	//
	// usage
	//

	/**
	 * @param region a contract region
	 * @return list of contracts in given region that were still available at the
	 *           last update of that region's contract, and for which we already
	 *           have fetched the data.
	 */
	public Map<Integer, ContractInfo> presentFetchedByRegion(int regionId) {
		return repo().findByRegionIdAndRemovedFalseAndFetchedTrue(regionId).stream()
		    .collect(Collectors.toMap(ContractInfo::getId, c -> c));
	}

	public Map<ContractRegion, Map<Integer, ContractInfo>> presentByRegion(Iterable<ContractRegion> regions) {
		return repo().findByRegionInAndRemovedFalse(regions).stream()
		    .collect(Collectors.groupingBy(ContractInfo::getRegion, Collectors.toMap(ContractInfo::getId, c -> c)));
	}


	//
	// cache management
	//

	/**
	 * notified when one+ item of a contract is modified
	 */
	public static interface ContractItemsListener extends EntityUpdateListener {
	}

	@Getter
	@Lazy
	private final Optional<List<ContractItemsListener>> listeners;

}
