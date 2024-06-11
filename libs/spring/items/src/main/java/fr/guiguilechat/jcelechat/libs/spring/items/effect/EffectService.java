package fr.guiguilechat.jcelechat.libs.spring.items.effect;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
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

	@Override
	protected void updateResponseOk(Effect data, Requested<R_get_dogma_effects_effect_id> response) {
		super.updateResponseOk(data, response);
		R_get_dogma_effects_effect_id received = response.getOK();
		if (received.discharge_attribute_id != 0) {
			data.setDischargeAttribute(attributeService.createIfAbsent(received.discharge_attribute_id));
		}
		if (received.duration_attribute_id != 0) {
			data.setDurationAttribute(attributeService.createIfAbsent(received.duration_attribute_id));
		}
		if (received.falloff_attribute_id != 0) {
			data.setFalloffAttribute(attributeService.createIfAbsent(received.falloff_attribute_id));
		}
		if (received.range_attribute_id != 0) {
			data.setRangeAttribute(attributeService.createIfAbsent(received.range_attribute_id));
		}
		if (received.tracking_speed_attribute_id != 0) {
			data.setTrackingSpeedAttribute(attributeService.createIfAbsent(received.tracking_speed_attribute_id));
		}
		modifierService.deleteForEffect(data);
		if (response.getOK().modifiers != null && response.getOK().modifiers.length > 0) {
			modifierService.saveAll(Stream.of(response.getOK().modifiers).map(
			    e -> convertModifier(e, data)).toList());
		}
	}

	protected Modifier convertModifier(get_dogma_effects_effect_id_modifiers e, Effect data) {
		Attribute modified = e.modified_attribute_id == 0
		    ? null
		    : attributeService.createIfAbsent(e.modified_attribute_id);
		Attribute modifying = e.modifying_attribute_id == 0
		    ? null
		    : attributeService.createIfAbsent(e.modifying_attribute_id);
		return Modifier.of(e, data, modified, modifying);
	}

}
