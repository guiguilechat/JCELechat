package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;

public interface ContractItemRepository extends IFetchedListElementRepository<ContractInfo, ContractItem> {

	@Query("""
select
	distinct(fetchResource)
from
	EsiMarketContractItem
where
	fetchResource.fetched
	and not fetchResource.removed
	and fetchResource.asksOneTypeForIsks
	and type.id=:typeId
""")
	public Stream<ContractInfo> listBOs(int typeId);

	@Query("""
	select
	distinct(fetchResource)
from
	EsiMarketContractItem
where
	fetchResource.fetched
	and not fetchResource.removed
	and fetchResource.offersOneTypeForIsk
	and type.id=:typeId
""")
	public Stream<ContractInfo> listSOs(int typeId);

	@Override
	@Modifying
	@Query("delete from EsiMarketContractItem where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

}
