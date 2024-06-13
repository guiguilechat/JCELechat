package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.items.attribute.AttributeService;
import fr.guiguilechat.jcelechat.libs.spring.items.effect.Effect;
import fr.guiguilechat.jcelechat.libs.spring.items.effect.EffectService;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_effects;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.type")
// depends on group effect attribute
@Order(3)
public class TypeService
	extends ARemoteFetchedResourceService<
        Type,
        Integer,
        R_get_universe_types_type_id,
        TypeRepository> {

	@Lazy
	private final AttributeService attributeService;

	@Lazy
	private final EffectService effectService;

	@Lazy
	private final GroupService groupService;

	@Override
	protected Type create(Integer entityId) {
		Type ret = new Type();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_types_type_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_universe_types((int) id, properties);
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE
		    .requestGetPages((page, props) -> ESIRawPublic.INSTANCE.get_universe_types(page, props), p);
	}

	@Override
	protected void updateResponseOk(Type data, Requested<R_get_universe_types_type_id> response) {
		super.updateResponseOk(data, response);
		R_get_universe_types_type_id received = response.getOK();
		data.setGroup(groupService.createIfAbsent(received.group_id));
		if (received.dogma_attributes != null) {
			for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes a : received.dogma_attributes) {
				Attribute att = attributeService.createIfAbsent(a.attribute_id);
				data.getAttributesValues().put(att, a.value);
			}
		}
		if (received.dogma_effects != null) {
			for (get_dogma_dynamic_items_type_id_item_id_dogma_effects e : received.dogma_effects) {
				Effect eff = effectService.createIfAbsent(e.effect_id);
				data.getEffectsDefault().put(eff, e.is_default);
			}
		}
	}

}
