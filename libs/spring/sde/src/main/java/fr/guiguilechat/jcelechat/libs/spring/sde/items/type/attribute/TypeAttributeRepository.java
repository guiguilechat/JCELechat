package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityRepository;

public interface TypeAttributeRepository extends DeducedEntityRepository<TypeAttribute> {

	List<TypeAttribute> findAllByAttributeId(int attributeId);

	List<TypeAttribute> findAllByTypeId(int typeId);

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

	@Query(value = """
select
    skId.value,
    max(skLvl.value)
from
    #{#entityName} skId
    join #{#entityName} skLvl on skLvl.type=skId.type
where
    skId.type.id in :typeIds
    and skId.value != 0
    and(
        (skId.attribute.id = 182 and skLvl.attribute.id=277)
        or(skId.attribute.id = 183 and skLvl.attribute.id=278)
        or(skId.attribute.id = 184 and skLvl.attribute.id=279)
        or(skId.attribute.id = 1285 and skLvl.attribute.id=1286)
        or(skId.attribute.id = 1289 and skLvl.attribute.id=1287)
        or(skId.attribute.id = 1290 and skLvl.attribute.id=1288)
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
