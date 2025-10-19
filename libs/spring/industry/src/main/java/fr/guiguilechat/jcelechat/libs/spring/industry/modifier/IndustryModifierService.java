package fr.guiguilechat.jcelechat.libs.spring.industry.modifier;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.attribute.FilteredAttributeReference;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryActivityModifierSources;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryActivityModifierSources.IndustryActivityModifier;
import fr.guiguilechat.jcelechat.libs.spring.gameclient.updater.GameClientUpdateListener;
import fr.guiguilechat.jcelechat.libs.spring.industry.activity.IndustryActivity;
import fr.guiguilechat.jcelechat.libs.spring.industry.activity.IndustryActivityService;
import fr.guiguilechat.jcelechat.libs.spring.industry.modifier.IndustryModifier.Modifiedfield;
import fr.guiguilechat.jcelechat.libs.spring.industry.targetfilter.IndustryTargetFilter;
import fr.guiguilechat.jcelechat.libs.spring.industry.targetfilter.IndustryTargetFilterService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.attribute.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.model.formula.industry.Activity;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
@Order(4) // depends on types, activities, filters, attributes
public class IndustryModifierService implements GameClientUpdateListener {

	final private IndustryModifierRepository repo;

	@Lazy
	private final AttributeService attributeService;

	@Lazy
	private final IndustryActivityService industryActivityService;

	@Lazy
	private final IndustryTargetFilterService industryTargetFilterService;

	@Lazy
	private final TypeService typeService;

	public List<IndustryModifier> saveAll(Iterable<IndustryModifier> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public IndustryModifier save(IndustryModifier entity) {
		return repo.saveAndFlush(entity);
	}

	public List<IndustryModifier> all() {
		return repo.findAll();
	}

	//
	// update from gameclient
	//

	@Override
	public void beforeGameClientUpdate() {
		repo.deleteAllInBatch();
	}

	@SneakyThrows
	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void onGameClientUpdate(ClientCache cache) {
		List<KeyValTime<EindustryActivityModifierSources>> loaded = EindustryActivityModifierSources.getLoader()
				.load(cache);
		List<Integer> typeIds = loaded.stream().map(kvt -> kvt.getKey()).distinct().toList();
		Map<Integer, Type> types = typeService.createIfAbsent(typeIds);
		List<Integer> attributeIds = loaded.stream()
				.map(kvt -> kvt.getVal()).filter(ams -> ams != null)
				.flatMap(ams -> Stream.of(ams.copying, ams.invention, ams.manufacturing, ams.reaction,
						ams.researchMaterial, ams.researchTime))
				.filter(am -> am != null)
				.flatMap(am -> Stream.of(am.cost, am.material, am.time)).filter(list -> list != null)
				.flatMap(list -> list.stream())
				.map(far -> far.attributeId)
				.distinct().toList();
		Map<Integer, Attribute> attributes = attributeService.createIfAbsent(attributeIds);
		Set<Integer> filterIds = loaded.stream()
				.map(kvt -> kvt.getVal()).filter(ams -> ams != null)
				.flatMap(ams -> Stream.of(ams.copying, ams.invention, ams.manufacturing, ams.reaction,
						ams.researchMaterial, ams.researchTime))
				.filter(am -> am != null)
				.flatMap(am -> Stream.of(am.cost, am.material, am.time)).filter(list -> list != null)
				.flatMap(list -> list.stream())
				.map(far -> far.filterId)
				.filter(i -> i != null)
				.collect(Collectors.toSet());
		Map<Integer, IndustryTargetFilter> filters = industryTargetFilterService.byId(filterIds);
		List<IndustryModifier> translated = loaded.stream()
				.flatMap(ktv -> modifiers(ktv, types, attributes, filters))
				.toList();
		saveAll(translated);
		log.info("imported {} modifiers", translated.size());
	}

	Stream<IndustryModifier> modifiers(KeyValTime<EindustryActivityModifierSources> kvt,
			Map<Integer, Type> types,
			Map<Integer, Attribute> attributes,
			Map<Integer, IndustryTargetFilter> filters) {
		if (kvt == null) {
			return Stream.of();
		}
		Type type = types.get(kvt.getKey());
		if (type == null) {
			throw new RuntimeException("type id " + kvt.getKey() + " not found");
		}
		return modifiers(kvt.getVal(), type, attributes, filters);
	}

	Stream<IndustryModifier> modifiers(EindustryActivityModifierSources iams, Type type,
			Map<Integer, Attribute> attributes, Map<Integer, IndustryTargetFilter> filters) {
		return Stream.of(
				modifiers(iams.copying, industryActivityService.byId().get(Activity.Type.copying.getId()), type,
						attributes, filters),
				modifiers(iams.invention, industryActivityService.byId().get(Activity.Type.invention.getId()), type,
						attributes, filters),
				modifiers(iams.manufacturing, industryActivityService.byId().get(Activity.Type.manufacturing.getId()),
						type,
						attributes, filters),
				modifiers(iams.reaction, industryActivityService.byId().get(Activity.Type.reaction.getId()), type,
						attributes, filters),
				modifiers(iams.researchMaterial,
						industryActivityService.byId().get(Activity.Type.researchMaterial.getId()), type,
						attributes, filters),
				modifiers(iams.researchTime, industryActivityService.byId().get(Activity.Type.researchTime.getId()),
						type,
						attributes, filters))
				.flatMap(s -> s);
	}

	Stream<IndustryModifier> modifiers(IndustryActivityModifier iam, IndustryActivity activity, Type type,
			Map<Integer, Attribute> attributes, Map<Integer, IndustryTargetFilter> filters) {
		if (iam == null) {
			return Stream.of();
		}
		return Stream.of(
				modifiers(iam.cost, Modifiedfield.cost, activity, type, attributes, filters),
				modifiers(iam.material, Modifiedfield.material, activity, type, attributes, filters),
				modifiers(iam.time, Modifiedfield.time, activity, type, attributes, filters))
				.flatMap(s -> s);
	}

	Stream<IndustryModifier> modifiers(List<FilteredAttributeReference> lfar, Modifiedfield mf,
			IndustryActivity activity, Type type,
			Map<Integer, Attribute> attributes, Map<Integer, IndustryTargetFilter> filters) {
		if (lfar == null || lfar.isEmpty()) {
			return Stream.of();
		}
		return lfar.stream().map(far -> modifier(far, mf, activity, type, attributes, filters));
	}

	IndustryModifier modifier(FilteredAttributeReference far, Modifiedfield mf, IndustryActivity activity, Type type,
			Map<Integer, Attribute> attributes, Map<Integer, IndustryTargetFilter> filters) {
		IndustryTargetFilter filter = far.filterId == null ? null : filters.get(far.filterId);
		Attribute attribute = attributes.get(far.attributeId);
		return new IndustryModifier(null, type, activity, filter, mf, attribute);
	}

}
