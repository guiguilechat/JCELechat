package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResourceService;
import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.contract.region")
@Order(2) // depends on region
public class ContractRegionService 
	extends ARemoteResourceService<
		ContractRegion,
		Integer,
    List<R_get_contracts_public_region_id>,
		ContractRegionRepository>{

	@Lazy
	private final ContractInfoService contractInfoService;

	@Lazy
	private final RegionService regionService;

	@Override
	protected Requested<List<R_get_contracts_public_region_id>> fetchData(Integer id, Map<String, String> properties) {
		Requested<List<R_get_contracts_public_region_id>> ret = ESIRawPublic.INSTANCE
		    .requestGetPages((p, props) -> ESIRawPublic.INSTANCE.get_contracts_public(p,
		        id, props), properties);
		return ret;
	}

	@Override
	protected ContractRegion create(Integer entityId) {
		ContractRegion ret = new ContractRegion();
		ret.setId(entityId);
		ret.setRegion(regionService.createIfAbsent(entityId));
		return ret;
	}

	@Override
	protected void updateResponseOk(Map<ContractRegion, List<R_get_contracts_public_region_id>> responseOk) {
		super.updateResponseOk(responseOk);
		for (Entry<ContractRegion, List<R_get_contracts_public_region_id>> e : responseOk.entrySet()) {
			createForRegion(e.getKey(), e.getValue());
		}
	}

	public void createForRegion(ContractRegion region, List<R_get_contracts_public_region_id> contracts) {
		Map<Integer, ContractInfo> existingPresent = contractInfoService.byRegionPresent(region);

		// contract stored that are no more present are marked as removed and to be
		// fetched again, to find out if they are completed (403) or canceled (404)
		Map<Integer, R_get_contracts_public_region_id> newById = contracts.stream()
		    .collect(Collectors.toMap(c -> c.contract_id, c -> c));
		List<ContractInfo> removed = new ArrayList<>();
		for (Entry<Integer, ContractInfo> e : existingPresent.entrySet()) {
			if (!newById.containsKey(e.getKey())) {
				e.getValue().setRemoved(true);
				e.getValue().setFetchActive(true);
				removed.add(e.getValue());
			}
		}
		contractInfoService.saveAll(removed);
		log.debug("{} contracts have been removed", removed.size());

		// then create the new ones
		List<Integer> missingIds = contracts.stream()
		    .filter(c -> !existingPresent.containsKey(c.contract_id))
		    .map(c -> c.contract_id)
		    .toList();
		Map<Integer, ContractInfo> newContracts = contractInfoService.createIfAbsent(missingIds);
		for (R_get_contracts_public_region_id c : contracts) {
			ContractInfo ci = newContracts.get(c.contract_id);
			if (ci != null) {
				ci.update(region, c);
			}
		}
		contractInfoService.saveAll(newContracts.values());
		log.debug("{} contracts have been created", newContracts.size());

	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_universe_regions(p).mapBody(List::of);
	}

}
