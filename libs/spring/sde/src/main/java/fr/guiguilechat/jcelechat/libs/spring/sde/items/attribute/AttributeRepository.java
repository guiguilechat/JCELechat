package fr.guiguilechat.jcelechat.libs.spring.sde.items.attribute;

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
    join SdeItemsTypeAttribute skId on skId.type=tp
    join SdeItemsTypeAttribute skLvl on skLvl.type=tp
where
    tp.id in :typeIds
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
