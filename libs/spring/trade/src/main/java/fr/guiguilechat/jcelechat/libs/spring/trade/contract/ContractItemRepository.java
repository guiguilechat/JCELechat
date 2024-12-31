package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Category;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;

public interface ContractItemRepository extends IFetchedListElementRepository<ContractInfo, ContractItem> {

	@Query("""
select
	distinct(type.group.category)
from
	EsiMarketContractItem
""")
	public List<Category> listCategories();

	@Override
	@Modifying
	@Query("delete from EsiMarketContractItem where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

}
