package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypeAttributeRepository extends JpaRepository<TypeAttribute, Long> {

	@Modifying
	@Query("delete from #{#entityName}")
	void delete();

	@Modifying
	@Query("delete from #{#entityName} where typeId in :typeIds")
	void deleteByTypeId(Iterable<Integer> typeIds);

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
