package fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractInfoService extends
		RemoteNumberEntityService<ContractInfo, Integer, ContractInfoRepository> {

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

	//
	// usage
	//

	/**
	 * @param region a contract region
	 * @return list of contracts in given region that were still available at the
	 *         last update of that region's contract, and for which we already
	 *         have fetched the data, with the key being the contract id
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
	 *         type and do not require an item
	 */
	public Stream<ContractInfo> exchangesSelling() {
		return repo().findByTypeAndFetchedTrueAndRemovedFalseAndOffersNonBpcTrueAndRequestsItemFalse(
				get_contracts_public_region_id_type.item_exchange);
	}

	/**
	 * @return a stream of the contracts that require at least an item and do not
	 *         offer an item
	 */
	public Stream<ContractInfo> exchangesBuying() {
		return repo().findByTypeAndFetchedTrueAndRemovedFalseAndRequestsItemTrueAndOffersItemFalse(
				get_contracts_public_region_id_type.item_exchange);
	}

	public static record ContractTypeVariant(int typeId, int me, int te, boolean copy) {

		public ContractTypeVariant(int typeId, Object[] arr) {
			this(typeId, ((Number) arr[0]).intValue(), ((Number) arr[1]).intValue(), (Boolean) arr[2]);
		}

		/**
		 * @return an int to compare them using (me+te) then me, so that it sorts first
		 *         ones as
		 *         (0,0),(0,1),(1,0),(0,2),(1,1),(2,0) etc.
		 */
		public int meteval() {
			return me * 100 + te * 101;
		}

	}

	public List<ContractTypeVariant> variants(int typeId) {
		return repo().listTypeVariants(typeId).stream().map(arr -> new ContractTypeVariant(typeId, arr)).toList();
	}

}
