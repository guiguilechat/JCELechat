package fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.IFetchedListElementRepositoryAutoId;

public interface ContractItemRepository extends IFetchedListElementRepositoryAutoId<ContractInfo, ContractItem> {

	@Query("""
select
	distinct(type.group.category)
from
	#{#entityName}
""") List<Category> listCategories();

	List<ContractItem> findWithTypeNameByFetchResourceOrderByTypeNameAsc(ContractInfo fetchResource);

}
