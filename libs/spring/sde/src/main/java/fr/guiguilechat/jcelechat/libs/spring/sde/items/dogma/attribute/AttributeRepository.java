package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface AttributeRepository extends SdeEntityRepository<Attribute, Integer> {

	@Query(value = """
select
    skId.value,
    max(skLvl.value)
from
    SdeItemsType tp
    join SdeItemsTypeAttribute skId on skId.typeId=tp.id
    join SdeItemsTypeAttribute skLvl on skLvl.typeId=tp.id
where
    tp.id in :typeIds
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
