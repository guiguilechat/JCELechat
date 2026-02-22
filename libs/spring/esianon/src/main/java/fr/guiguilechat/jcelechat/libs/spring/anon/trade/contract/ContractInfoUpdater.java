package fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.CacheInvalidator;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConfigurationProperties(prefix = "esi.trade.contract.info")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractInfoUpdater extends
		RemoteNumberEntityUpdater<ContractInfo, Integer, R_get_contracts_public_items_contract_id[], ContractInfoRepository, ContractInfoService> {

	@Lazy
	private final ContractItemService contractItemService;

	@Lazy
	private final TypeService typeService;

	public int pessimisticFetchAge = 7;

	@Override
	protected Requested<R_get_contracts_public_items_contract_id[]> fetchData(Integer id,
			Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.requestGetPages(
				(page, props) -> ESIRawPublic.INSTANCE.get_contracts_public_items(id, page, props), properties)
				.mapBody(l -> l.toArray(R_get_contracts_public_items_contract_id[]::new));
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
			ret = repo().findByFetchActiveTrueAndExpiresBeforeOrderByFetchPriorityDescExpiresAsc(
					Instant.now(),
					Limit.of(batchSize));
			if (!ret.isEmpty()) {
				log.debug("will fail {} contrat info requests to deduce their end state", ret.size());
			}
		} else {
			ContractInfo firstCI = ret.get(0);
			// if the first contract is older than a day, it's likely to fail (we are
			// catching up on old contracts) : only fetch
			// with pessimistic batch size, so half the remaining esi errors
			if (ChronoUnit.DAYS.between(firstCI.getDateIssued(), Instant.now()) >= pessimisticFetchAge) {
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
				log.trace(" finished analyzing {} contracts in {} ms (listIds={} fetch={} analyze+save={})",
						list.size(),
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
	// cache management
	//

	/**
	 * notified when one+ item of a contract is modified
	 */
	public interface ContractItemsListener extends CacheInvalidator {
	}

	@Getter
	@Lazy
	private final Optional<List<ContractItemsListener>> listeners;

}
