package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface MutaMappingRepository extends DeducedEntityRepository<MutaMapping> {

	public static record MutaProduct(int categoryId, String categoryName,
			int groupId, String groupName,
			int typeId, String typeName) {
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
	typ.group.category.id,
	typ.group.category.name,
	typ.group.id,
	typ.group.name,
	typ.id,
	typ.name
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
