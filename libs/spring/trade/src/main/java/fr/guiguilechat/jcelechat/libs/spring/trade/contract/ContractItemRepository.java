package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.list.IFetchedListElementRepository;

public interface ContractItemRepository extends IFetchedListElementRepository<ContractInfo, ContractItem> {

	@Query("""
select
	distinct(contract)
from
	EsiMarketContractItem
where
	contract.fetched
	and not contract.removed
	and contract.asksOneTypeForIsks
	and type.id=:typeId
""")
	public Stream<ContractInfo> listBOs(int typeId);

	@Query("""
	select
	distinct(contract)
from
	EsiMarketContractItem
where
	contract.fetched
	and not contract.removed
	and contract.offersOneTypeForIsk
	and type.id=:typeId
""")
	public Stream<ContractInfo> listSOs(int typeId);

}
