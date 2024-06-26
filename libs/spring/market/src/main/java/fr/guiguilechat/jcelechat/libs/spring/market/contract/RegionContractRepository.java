package fr.guiguilechat.jcelechat.libs.spring.market.contract;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;

public interface RegionContractRepository extends JpaRepository<RegionContract, Long> {

	public List<RegionContract> findTop1000ByTypeAndFetchedFalseAndRemovedFalseOrderByContractId(
			get_contracts_public_region_id_type type);

	public List<RegionContract> findByRegionRegionIdAndRemovedFalse(int regionId);

	@Query("""
select
	count(*)
from
	EsiMarketRegionContract
where
	not fetched
	and not removed
	and type in :type
""")
	public int countMissingItems(get_contracts_public_region_id_type type);

	@Query("""
select
	distinct(contract)
from
	EsiMarketRegionContractItem
where
	contract.fetched
	and not contract.removed
	and contract.asksOneTypeForIsks
	and typeId=:typeId
""")
	public Stream<RegionContract> listBOs(int typeId);

	@Query("""
	select
	distinct(contract)
from
	EsiMarketRegionContractItem
where
	contract.fetched
	and not contract.removed
	and contract.offersOneTypeForIsk
	and typeId=:typeId
""")
	public Stream<RegionContract> listSOs(int typeId);

	/*
	 select
 item.type_id,
 count(*)
from
 ESI_MARKET_REGIONCONTRACTITEM item
 join ESI_MARKET_REGIONCONTRACT contract on item.contract_id=contract.id
where
 contract.TYPE ='item_exchange'
 and not contract.removed
 and contract.offers_one_type_for_isk
 and not item.blueprint_copy
group by
 item.type_id
order by
  count(*) desc
	 */
}
