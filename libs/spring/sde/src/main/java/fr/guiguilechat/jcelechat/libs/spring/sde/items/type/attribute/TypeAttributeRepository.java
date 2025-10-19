package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.attribute.Attribute;

public interface TypeAttributeRepository extends JpaRepository<TypeAttribute, Long> {

	@Modifying
	@Query("delete from #{#entityName} where type.id in :typeIds")
	void deleteByTypeId(Iterable<Integer> typeIds);

	List<TypeAttribute> findAllByAttributeId(int attributeId);

	List<TypeAttribute> findAllByAttribute(Attribute attribute);

	@Query("""
select
	ta.type.id type_id,
	ta.value val
from
	#{#entityName} ta
where
	ta.attribute.id=:attributeId
	and ta.type.id in :typeIds
""")
	List<Object[]> listValuesByAttributeIdTypeIdIn(int attributeId, Iterable<Integer> typeIds);

}
