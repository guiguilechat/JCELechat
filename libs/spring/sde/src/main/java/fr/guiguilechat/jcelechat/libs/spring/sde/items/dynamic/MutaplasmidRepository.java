package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityRepository;

public interface MutaplasmidRepository extends SdeEntityRepository<Mutaplasmid, Integer> {

	public record AttributeRange(int attributeId, String attributeName, boolean highIsGood, Number min, Number max) {

		/**
		 * @return chance (base 1) to have required value
		 */
		public double probability(Number required) {
			if (highIsGood()) {
				// need to be higher than required
				if (required.doubleValue() <= min.doubleValue()) {
					return 1.0;
				}
				if (required.doubleValue() >= max.doubleValue()) {
					return 0.0;
				}
				return (max.doubleValue() - required.doubleValue()) / (max.doubleValue() - min.doubleValue());
			} else {
				// need to be lower than required
				if (required.doubleValue() >= max.doubleValue()) {
					return 1.0;
				}
				if (required.doubleValue() <= min.doubleValue()) {
					return 0.0;
				}
				return (min.doubleValue() - required.doubleValue()) / (min.doubleValue() - max.doubleValue());
			}
		}

	}

	@Query("""
from
	#{#entityName} muta
	join muta.mappings mapping
	join muta.multipliers multi
	join mapping.applicable sourceType
	join SdeItemsTypeAttribute attValue on attValue.typeId=sourceType.id and attValue.attributeId=multi.attribute.id
where
	mapping.product.id=:productTypeId
group by multi.attribute
select
	multi.attribute.id,
	multi.attribute.name,
	max(coalesce(multi.highIsGood, multi.attribute.highIsGood)),
	least(min(multi.min*attValue.value), min(multi.max*attValue.value)),
	greatest(max(multi.max*attValue.value), max(multi.min*attValue.value))
order by multi.attribute.name
""")
	List<AttributeRange> listAttributesRanges(int productTypeId);

	public record MutationCouple(Type sourceType, Mutaplasmid mutaplasmid) {
	}

	@Query("""
from
	#{#entityName} muta
	join muta.mappings mapping
	join mapping.applicable sourceType
where
	mapping.product.id=:productTypeId
select
	sourceType,
	muta
""")
	List<MutationCouple> listPossibleMutations(int productTypeId);

}
