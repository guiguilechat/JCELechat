package fr.guiguilechat.jcelechat.libs.spring.items.effect;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.items.attribute.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_effects_effect_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_effects_effect_id_modifiers;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.effect")
@Order(2)
public class EffectService
    extends ARemoteFetchedResourceService<Effect, Integer, R_get_dogma_effects_effect_id, EffectRepository> {

	@Lazy
	private final ModifierService modifierService;

	@Lazy
	private final AttributeService attributeService;

	@Override
	protected Effect create(Integer entityId) {
		Effect ret = new Effect();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_dogma_effects_effect_id> fetchData(Integer id, Map<String, String> properties) {
		Requested<R_get_dogma_effects_effect_id> ret = ESIRawPublic.INSTANCE.get_dogma_effects(id, properties);
		return ret;
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_dogma_effects(p).mapBody(List::of);
	}

	protected void updateResponseOk(Effect data, R_get_dogma_effects_effect_id received,
	    Map<Integer, Attribute> idToAttribute) {
		if (received.discharge_attribute_id != 0) {
			data.setDischargeAttribute(idToAttribute.get(received.discharge_attribute_id));
		}
		if (received.duration_attribute_id != 0) {
			data.setDurationAttribute(idToAttribute.get(received.duration_attribute_id));
		}
		if (received.falloff_attribute_id != 0) {
			data.setFalloffAttribute(idToAttribute.get(received.falloff_attribute_id));
		}
		if (received.range_attribute_id != 0) {
			data.setRangeAttribute(idToAttribute.get(received.range_attribute_id));
		}
		if (received.tracking_speed_attribute_id != 0) {
			data.setTrackingSpeedAttribute(idToAttribute.get(received.tracking_speed_attribute_id));
		}
		modifierService.deleteForEffect(data);
		if (received.modifiers != null && received.modifiers.length > 0) {
			modifierService.saveAll(Stream.of(received.modifiers).map(
			    e -> convertModifier(e, data, idToAttribute)).toList());
		}
	}

	protected Modifier convertModifier(get_dogma_effects_effect_id_modifiers e, Effect data,
	    Map<Integer, Attribute> idToAttribute) {
		Attribute modified = e.modified_attribute_id == 0
		    ? null
		    : idToAttribute.get(e.modified_attribute_id);
		Attribute modifying = e.modifying_attribute_id == 0
		    ? null
		    : idToAttribute.get(e.modifying_attribute_id);
		return Modifier.of(e, data, modified, modifying);
	}

	@Override
	protected void updateResponseOk(Map<Effect, R_get_dogma_effects_effect_id> responseOk) {
		super.updateResponseOk(responseOk);

		Map<Integer, Attribute> idToAttribute = attributeService
		    .createIfAbsent(responseOk.values().stream()
		        .flatMap(r -> Stream.concat(
		            Stream.of(r.discharge_attribute_id, r.duration_attribute_id,
		                r.falloff_attribute_id, r.range_attribute_id, r.tracking_speed_attribute_id),
		            r.modifiers == null ? Stream.empty()
		                : Stream.of(r.modifiers)
		                    .flatMap(m -> Stream.of(m.modified_attribute_id, m.modifying_attribute_id))))
		        .filter(o -> o != null && o != 0).distinct().toList());
		responseOk.entrySet().stream()
		    .forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToAttribute));
	}

}
