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

	@Query(value = """
select
    skId.value,
    max(skLvl.value)
from
    #{#entityName} skId
    join #{#entityName} skLvl on skLvl.typeId=skId.typeId
where
    skId.typeId in :typeIds
    and skId.value != 0
    and(
        (skId.attributeId = 182 and skLvl.attributeId=277)
        or(skId.attributeId = 183 and skLvl.attributeId=278)
        or(skId.attributeId = 184 and skLvl.attributeId=279)
        or(skId.attributeId = 1285 and skLvl.attributeId=1286)
        or(skId.attributeId = 1289 and skLvl.attributeId=1287)
        or(skId.attributeId = 1290 and skLvl.attributeId=1288)
    )
group by
    skId.value
order by
    skId.value
""")
	/**
	 * fetch the [skillId, skillLevel] couples required to board/use the list of
	 * types provided. Those should be cast as Number.
	 */
	List<Object[]> requiredSkills(Iterable<Integer> typeIds);
}
