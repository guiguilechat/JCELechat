package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface TypeAttributeRepository extends DeducedEntityRepository<TypeAttribute> {

	List<TypeAttribute> findAllByAttributeId(int attributeId);

	List<TypeAttribute> findAllByTypeId(int typeId);

	@Query("""
select
	ta.typeId type_id,
	ta.value val
from
	#{#entityName} ta
where
	ta.attributeId=:attributeId
	and ta.typeId in :typeIds
""")
	List<Object[]> listValuesByAttributeIdTypeIdIn(int attributeId, Iterable<Integer> typeIds);

}
