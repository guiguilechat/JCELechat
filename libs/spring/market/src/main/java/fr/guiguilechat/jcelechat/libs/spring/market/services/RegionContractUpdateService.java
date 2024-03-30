package fr.guiguilechat.jcelechat.libs.spring.market.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;
import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionContract;
import fr.guiguilechat.jcelechat.libs.spring.market.model.RegionContractItem;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionContractUpdateService {

	final private CacheManager cacheManager;

	private final RegionContractItemService regionContractItemService;

	private final RegionContractService regionContractService;

	final private ObservedRegionService orService;

	private ForkJoinPool highParrallelPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 10);

	// contracts management

	public static interface ContractUpdateListener {

		public default List<String> listContractCaches(int regionId) {
			return List.of();
		}

		public default void onContractsUpdate(ObservedRegion region, List<RegionContract> removed) {

		}

	}

	private final List<ContractUpdateListener> updateContractListeners;

	protected void notifyContractListeners(ObservedRegion region, List<RegionContract> removed) {
		updateContractListeners.stream().flatMap(l -> l.listContractCaches(region.getRegionId()).stream())
				.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
		updateContractListeners.stream().forEach(l -> l.onContractsUpdate(region, removed));
	}

	@Async
	@Transactional
	public CompletableFuture<Void> updateContractLines(ObservedRegion region) {
		long startMs = System.currentTimeMillis();
		Map<Long, R_get_contracts_public_region_id> received = fetchContractsNoDB(region).stream()
				.collect(Collectors.toMap(l -> (long) l.contract_id, l -> l));
		long retrievedMs = System.currentTimeMillis();
		if (received != null) {
			Map<Long, RegionContract> alreadyStored = regionContractService.allPresentInRegionById(region.getRegionId());
			List<RegionContract> updated = new ArrayList<>();
			List<RegionContract> removed = new ArrayList<>();
			int created = 0;
			for (RegionContract rc : alreadyStored.values()) {
				if (!received.containsKey(rc.getContractId())) {
					rc.setRemoved(true);
					removed.add(rc);
					updated.add(rc);
				}
			}
			for (R_get_contracts_public_region_id r : received.values()) {
				if (!alreadyStored.containsKey((long) r.contract_id)) {
					created++;
					updated.add(RegionContract.of(region, r));
				}
			}
			long applyMS = System.currentTimeMillis();
			orService.save(region);
			regionContractService.saveAll(updated);
			long saveMs = System.currentTimeMillis();
			log.debug(" updated contracts for region " + region.getRegionId()
					+ " received " + received.size() + " contracts, removed " + removed.size() + " ,created " + created
					+ ", in : "
					+ " fetch=" + (int) Math.ceil(0.001 * (retrievedMs - startMs)) + "s"
					+ " apply=" + (int) Math.ceil(0.001 * (applyMS - retrievedMs)) + "s"
					+ " save=" + (int) Math.ceil(0.001 * (saveMs - applyMS)) + "s");
			notifyContractListeners(region, removed);
		}

		return CompletableFuture.completedFuture(null);
	}

	/**
	 * fetch the multi pages of contracts for given region
	 *
	 * @return null if no change to be stored, be it network error, same cache, etc.
	 *           Otherwise the new list of contracts
	 */
	List<R_get_contracts_public_region_id> fetchContractsNoDB(ObservedRegion region) {
		long startTime = System.currentTimeMillis();
		boolean failed = false;
		boolean noChange = false;
		Set<String> errors = new HashSet<>();
		Integer pages = null;
		List<R_get_contracts_public_region_id> fetchedLines = new ArrayList<>();

		Map<String, String> properties = new HashMap<>();
		String lastEtag = region.getLastContractsEtag();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<R_get_contracts_public_region_id[]> firstResult = ESIStatic.INSTANCE.get_contracts_public(null,
				region.getRegionId(), properties);
		long firstPageTime = System.currentTimeMillis();
		String newEtag = firstResult.getETag();
		int responseCode = firstResult.getResponseCode();
		switch (responseCode) {
			case 200:
				pages = firstResult.getNbPages();
			break;
			case 304:
				noChange = true;
			break;
			default:
				failed = true;
				errors.add(firstResult.getError());
		}
		Long allPagesTime = null;
		if (!failed && !noChange) {
			fetchedLines.addAll(Arrays.asList(firstResult.getOK()));
			boolean expireMismatch = false;
			if (pages != null && pages > 1) {
				IntStream is = IntStream.rangeClosed(2, pages);
				List<Requested<R_get_contracts_public_region_id[]>> nextPages = null;
				try {
					nextPages = highParrallelPool.submit(() -> is.parallel()
							.mapToObj(p -> ESIStatic.INSTANCE.get_contracts_public(p, region.getRegionId(), properties))
							.toList())
							.get();
				} catch (InterruptedException | ExecutionException e) {
					failed = true;
					log.error("while fetching next page for contracts in region " + region.getRegionId(), e);
					errors.add(e.getMessage());
				}
				allPagesTime = System.currentTimeMillis();
				if (!failed) {
					for (Requested<R_get_contracts_public_region_id[]> pageResult : nextPages) {
						if (!pageResult.isOk()) {
							failed = true;
							responseCode = pageResult.getResponseCode();
							errors.add(pageResult.getError());
						} else if (!Objects.equals(firstResult.getExpires(), pageResult.getExpires())) {
							log.error("mismatching expire for " + pageResult.getURL());
							expireMismatch = true;
						} else {
							fetchedLines.addAll(Arrays.asList(pageResult.getOK()));
						}
					}
					if (expireMismatch) {
						failed = true;
						errors.add("Expires mismatch");
					}
				}
			}
		}
		if (failed || noChange) {
			return null;
		}
		long endTime = System.currentTimeMillis();
		log.debug(" fetched contracts for region(" + region.getRegionId() + ") result="
				+ (failed ? "fail:" + errors : noChange ? "noChange" : fetchedLines.size() + "lines") + " in "
				+ (endTime - startTime) + " ms(firstPage=" + (firstPageTime - startTime) + (allPagesTime == null ? ""
						: " next" + pages + "Pages=" + (allPagesTime - firstPageTime) + " process=" + (endTime - allPagesTime))
				+ ")");
		region.setLastMarketEtag(newEtag);
		return fetchedLines;
	}

	// contracts items

	public static interface ContractItemsUpdateListener {

		public default List<String> listContractItemsCaches() {
			return List.of();
		}

		public default void onContractsItemsUpdate(RegionContract contract) {

		}

	}

	private final List<ContractItemsUpdateListener> updateContractItemsListeners;

	@Async
	@Transactional
	public CompletableFuture<Void> updateContractItems(RegionContract contract) {
		List<R_get_contracts_public_items_contract_id> items = fetchContractsItemsNoDB(contract);
		if (items != null) {
			if (contract.isRemoved()) {
				notifyContractListeners(contract.getRegion(), List.of(contract));
			} else {
				regionContractItemService.saveAll(
						items.stream().map(line -> RegionContractItem.of(contract, line)).toList());
			}
			regionContractService.save(contract);
		}
		updateContractItemsListeners.stream().flatMap(l -> l.listContractItemsCaches().stream())
				.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
		updateContractItemsListeners.stream().forEach(l -> l.onContractsItemsUpdate(contract));
		return CompletableFuture.completedFuture(null);
	}

	/**
	 * fetch the items of a contract without DB access
	 *
	 * @return null in case of error. Otherwise the contract is updated to reflect
	 *           the items fetched, they need to be saved and he also needs.
	 */
	List<R_get_contracts_public_items_contract_id> fetchContractsItemsNoDB(RegionContract contract) {
		long startTime = System.currentTimeMillis();
		Integer pages = null;
		List<R_get_contracts_public_items_contract_id> fetchedItems = new ArrayList<>();

		Requested<R_get_contracts_public_items_contract_id[]> firstResult = ESIStatic.INSTANCE.get_contracts_public_items(
				(int) contract.getContractId(), null, null);
		long firstPageTime = System.currentTimeMillis();
		int responseCode = firstResult.getResponseCode();
		switch (responseCode) {
			case 200:
				pages = firstResult.getNbPages();
			break;
			case 403:
			case 404:
				contract.setRemoved(true);
				return List.of();
			default:
				return null;
		}
		Long allPagesTime = null;
		fetchedItems.addAll(Arrays.asList(firstResult.getOK()));
		if (pages != null && pages > 1) {
			IntStream is = IntStream.rangeClosed(2, pages);
			List<Requested<R_get_contracts_public_items_contract_id[]>> nextPages = null;
			try {
				nextPages = highParrallelPool.submit(() -> is.parallel()
						.mapToObj(p -> ESIStatic.INSTANCE.get_contracts_public_items((int) contract.getContractId(), p, null))
						.toList())
						.get();
			} catch (InterruptedException | ExecutionException e) {
				log.error("while fetching next page for items of contractId" + contract.getContractId(), e);
				return null;
			}
			allPagesTime = System.currentTimeMillis();
			boolean failed = false;
			for (Requested<R_get_contracts_public_items_contract_id[]> pageResult : nextPages) {
				if (!pageResult.isOk()) {
					log.debug("failed to fetch items for contract " + contract.getContractId() + " received eror"
							+ pageResult.getError() + " code " + pageResult.getResponseCode());
					failed = true;
				} else {
					fetchedItems.addAll(Arrays.asList(pageResult.getOK()));
				}
			}
			if (failed) {
				return null;
			}
		}
		Set<Integer> askedTypesIds = new HashSet<>();
		Set<Integer> offeredTypesIds = new HashSet<>();
		boolean hasBPC = false;
		for (R_get_contracts_public_items_contract_id item : fetchedItems) {
			if (item.is_included) {
				if (!item.is_blueprint_copy) {
					offeredTypesIds.add(item.type_id);
				}
			} else {
				askedTypesIds.add(item.type_id);
			}
			if (item.is_blueprint_copy) {
				hasBPC=true;
			}
		}
		contract.setNbTypesAsked(askedTypesIds.size());
		contract.setAsksOneTypeForIsks(!hasBPC && offeredTypesIds.isEmpty() && askedTypesIds.size() == 1);
		contract.setNbTypesIncluded(offeredTypesIds.size());
		contract.setOffersOneTypeForIsk(!hasBPC && offeredTypesIds.size() == 1 && askedTypesIds.isEmpty());
		contract.setOffersBpcForIsk(hasBPC && offeredTypesIds.size() == 1 && askedTypesIds.isEmpty());
		contract.setFetched(true);

		long endTime = System.currentTimeMillis();
		log.debug(" fetched items for contractId(" + contract.getContractId() + ") result="
				+ fetchedItems.size() + "items" + " in "
				+ (endTime - startTime) + " ms(firstPage=" + (firstPageTime - startTime) + (allPagesTime == null ? ""
						: " next" + pages + "Pages=" + (allPagesTime - firstPageTime) + " process=" + (endTime - allPagesTime))
				+ ")");
		return fetchedItems;
	}

}
