package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface MutaMappingRepository extends DeducedEntityRepository<MutaMapping> {

	public static record MutaProduct(
			Category category,
			Group group,
			Type type) {
	}

	@Query("""
from
	(select
		distinct(mapp.product) typ
	from
		#{#entityName} mapp
		join mapp.applicable sourceType
		join SdeItemsType mutaType on mapp.mutaplasmid.id=mutaType.id
	where
		mutaType.published
		and mutaType.marketGroup is not null
		and sourceType.published
		and sourceType.marketGroup is not null
	)
select
	typ.group.category,
	typ.group,
	typ
order by
	typ.group.category.name,
	typ.group.name,
	typ.name
""")
	List<MutaProduct> listProducts();

	@Query("""
from
	#{#entityName} map
	join map.applicable source
where
	map.product.id=:abyssalTypeId
	and source.published
	and source.marketGroup is not null
select
	distinct(source)
order by
	source.metaGroup.id,
	source.basePrice
""")
	List<Type> sourcesFor(int abyssalTypeId);

}
