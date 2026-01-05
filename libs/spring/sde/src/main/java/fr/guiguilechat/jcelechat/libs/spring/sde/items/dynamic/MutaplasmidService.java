package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.MutaplasmidRepository.AttributeRange;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.MutaplasmidRepository.MutationCouple;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.multiplier.MutaMultiplier;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.multiplier.MutaMultiplierService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
public class MutaplasmidService extends SdeEntityService<Mutaplasmid, Integer, MutaplasmidRepository>
		implements SdeListener {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private AttributeService attributeService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private MutaMultiplierService mutaMultiplierService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TypeAttributeService typeAttributeService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TypeService typeService;

	public MutaplasmidService() {
		super(Mutaplasmid::new);
	}

	/**
	 * list mutated attributes ranges based on the each (source type, mutaplasmids
	 * used) to produce a mutated item.
	 *
	 * @param productTypeId target mutated (abyssal) item to produce
	 */
	@Cacheable("mutationProductRanges")
	public List<AttributeRange> listAttributesRanges(int productTypeId) {
		return repo().listAttributesRanges(productTypeId);
	}

	public static record MutatedRange(Type sourceType, Type mutaplasmid, Map<Integer, AttributeRange> attributes) {

		/**
		 * @param requiredAttributes map of attributes to required value
		 * @return
		 */
		public double chance(Map<Integer, Number> requiredAttributes) {
			double ret =1.0;
			for(Entry<Integer, Number> e : requiredAttributes.entrySet()) {
				if (attributes != null) {
					var range = attributes.get(e.getKey());
					if (range != null) {
						double rangeProbability = range.probability(e.getValue());
//						System.out.println(
//								"" + range + "has " + rangeProbability + " probbability to get attribute limit " + e);
						ret *= rangeProbability;
						if (ret <= 0.0) {
							return ret;
						}
					}
				}
			}
			return ret;
		}

		public static MutatedRange of(Type sourceType,
				Type mutaplasmid,
				Map<Integer, Number> typeAttributes,
				Map<Integer, MutaMultiplier> attributeMultipliers,
				Map<Integer, String> attributeId2Name,
				Map<Integer, Boolean> attributeId2HighIsGood) {
			Map<Integer, AttributeRange> attributes = new HashMap<>();
			for (Entry<Integer, Number> e : typeAttributes.entrySet()) {
				int attributeId = e.getKey();
				String attName = attributeId2Name.computeIfAbsent(attributeId, i -> "attribute:" + i);
				Number baseValue = typeAttributes.getOrDefault(attributeId, 0.0);
				Number min = baseValue, max = baseValue;
				boolean highIsGood = attributeId2HighIsGood.getOrDefault(attributeId, true);
				MutaMultiplier multi = attributeMultipliers.get(e.getKey());
				if (multi != null) {
					min = new BigDecimal(min.doubleValue()
							* (min.doubleValue() < 0 ? multi.getMax().doubleValue() : multi.getMin().doubleValue()));
					max = new BigDecimal(max.doubleValue()
							* (max.doubleValue() < 0 ? multi.getMin().doubleValue() : multi.getMax().doubleValue()));
				}
				attributes.put(attributeId, new AttributeRange(attributeId, attName, highIsGood, min, max));
			}
			return new MutatedRange(sourceType, mutaplasmid, attributes);
		}
	}

	/**
	 * @param productTypeId an abyssal type id.
	 * @return the possible (source, mutaplasmid) alongside their attributes ranges.
	 *         Include those without a mutation (mutaplasmid=null)
	 */
	@Cacheable("mutationProductOutputs")
	public List<MutatedRange> listOutputs(int productTypeId) {
		// list possible mutations
		List<MutationCouple> mutations = repo().listPossibleMutations(productTypeId);
		// fetch all static data
		Map<Integer, Type> sourceTypeId2Type = mutations.stream()
				.map(MutationCouple::sourceType)
				.distinct()
				.collect(Collectors.toMap(Type::getId, m -> m));
		Map<Integer, Map<Integer, Number>> typeId2attributeId2value = typeAttributeService()
				.byTypeId(sourceTypeId2Type.keySet())
				.stream()
				.collect(Collectors.groupingBy(TypeAttribute::getTypeId))
				.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						e -> e.getValue().stream()
								.collect(Collectors.toMap(
										TypeAttribute::getAttributeId,
										TypeAttribute::getValue))));
		List<Integer> mutaplasmidTypeIds = mutations.stream()
				.map(m -> m.mutaplasmid().getId())
				.distinct()
				.toList();
		Map<Integer, Map<Integer, MutaMultiplier>> muta2attribute2mult = mutaMultiplierService()
				.byMutaByAttribtute(mutaplasmidTypeIds);
		Map<Integer, Type> mutaplasmidId2Type = typeService.mapOfIdById(mutaplasmidTypeIds);

		// all the attributes in the multipliers and the types
		List<Attribute> attributes = attributeService().ofId(
				Stream.concat(
						muta2attribute2mult.values().stream()
								.flatMap(m -> m.values().stream())
								.map(mult -> mult.getAttribute().getId()),
						typeId2attributeId2value.values().stream()
								.flatMap(m -> m.keySet().stream()))
						.collect(Collectors.toSet()));
		Map<Integer, Boolean> attributeId2HighIsGood = new HashMap<>();
		muta2attribute2mult.values().stream()
				.flatMap(m -> m.values().stream()).forEach(mm -> {
					attributeId2HighIsGood.computeIfAbsent(mm.getAttribute().getId(), _ -> mm.getHighIsGood());
				});
		attributes.forEach(att -> {
			attributeId2HighIsGood.computeIfAbsent(att.getId(), _ -> att.isHighIsGood());
		});
		Map<Integer, String> attributeId2Name = attributes.stream()
				.collect(Collectors.toMap(Attribute::getId, Attribute::getName));

		List<MutatedRange> ret = new ArrayList<>();
		// add the mutations ranges
		mutations.forEach(mutation -> {
			Map<Integer, Number> attributesMap = typeId2attributeId2value.get(mutation.sourceType().getId());
			Map<Integer, MutaMultiplier> multipliers = muta2attribute2mult.get(mutation.mutaplasmid().getId());
			Type mutaplasmidType = mutaplasmidId2Type.get(mutation.mutaplasmid().getId());
			ret.add(MutatedRange.of(
					mutation.sourceType(),
					mutaplasmidType,
					attributesMap,
					multipliers,
					attributeId2Name,
					attributeId2HighIsGood));
		});
		// add the unmutated ranges
		sourceTypeId2Type.values().forEach(st -> {
			Map<Integer, Number> attributesMap = typeId2attributeId2value.get(st.getId());
			ret.add(MutatedRange.of(
					st,
					null,
					attributesMap,
					Map.of(),
					attributeId2Name,
					attributeId2HighIsGood));
		});
		return ret;
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final List<String> listSDECaches = List.of(
			"mutationProductOutputs",
			"mutationProductRanges"
	//
	);

}
