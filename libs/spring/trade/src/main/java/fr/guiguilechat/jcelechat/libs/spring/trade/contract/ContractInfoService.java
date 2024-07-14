package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

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
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResourceService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.trade.contract.info")
@Order(4) // depends on contract region, type for the items
public class ContractInfoService extends ARemoteResourceService<
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
	
	//
	// usage
	//

	public Map<Integer, ContractInfo> byRegionPresent(ContractRegion region) {
		return repo().findByRegionAndRemovedFalse(region).stream()
		    .collect(Collectors.toMap(ContractInfo::getId, c -> c));
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
