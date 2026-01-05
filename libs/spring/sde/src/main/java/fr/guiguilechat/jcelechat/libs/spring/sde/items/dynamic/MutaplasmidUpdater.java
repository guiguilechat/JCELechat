package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdynamicItemAttributes;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdynamicItemAttributes.MinMax;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdynamicItemAttributes.TransformTypes;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping.MutaMapping;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping.MutaMappingService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.multiplier.MutaMultiplier;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.multiplier.MutaMultiplierService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityUpdater;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Service
@ConfigurationProperties(prefix = "sde.items.mutaplasmid")
public class MutaplasmidUpdater
		extends SdeEntityUpdater<Mutaplasmid, MutaplasmidRepository, MutaplasmidService, EdynamicItemAttributes> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private AttributeService attributeService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private MutaMappingService mutaMappingService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private MutaMultiplierService mutaMultiplierService;

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private TypeService typeService;

	public MutaplasmidUpdater() {
		super(EdynamicItemAttributes.LOADER);
	}

	@Override
	protected void processSource(LinkedHashMap<Integer, EdynamicItemAttributes> sources) {
		mutaMappingService().delete();
		mutaMultiplierService.delete();
		var getMutaplasmid = service().getter(sources.keySet());
		// all the types referenced
		Stream<Integer> typeIds = sources.entrySet().stream().flatMap(edia -> Stream.concat(
				Stream.of(edia.getKey()),
				edia.getValue().inputOutputMapping.stream()
						.flatMap(tt -> Stream.concat(
								Stream.of(tt.resultingType),
								tt.applicableTypes.stream()))));
		var getType = typeService().getter(typeIds);
		Stream<Integer> attributeIds = sources.values().stream()
				.flatMap(edia -> edia.attributeIDs.keySet().stream());
		var getAttribute = attributeService().getter(attributeIds);
		List<Mutaplasmid> mutas = new ArrayList<>();
		List<MutaMapping> mappings = new ArrayList<>();
		List<MutaMultiplier> multipliers = new ArrayList<>();
		for (Entry<Integer, EdynamicItemAttributes> e : sources.entrySet()) {
			var muta = getMutaplasmid.apply(e.getKey());
			muta.receivedSource();
			for (TransformTypes tt : e.getValue().inputOutputMapping) {
				Type product = getType.apply(tt.resultingType);
				var applicable = tt.applicableTypes.stream().map(getType::apply).toList();
				mappings.add(MutaMapping.builder()
						.applicable(applicable)
						.mutaplasmid(muta)
						.product(product)
						.build());
			}
			for (Entry<Integer, MinMax> mm : e.getValue().attributeIDs.entrySet()) {
				multipliers.add(
						MutaMultiplier.of(
								muta,
								getAttribute.apply(mm.getKey()),
								mm.getValue().highIsGood,
								mm.getValue().min,
								mm.getValue().max));
			}

		}
		repo().saveAllAndFlush(mutas);
		mutaMappingService().saveAll(mappings);
		mutaMultiplierService().saveAll(multipliers);
	}

}
