package fr.guiguilechat.jcelechat.libs.spring.items.attribute;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.attribute")
@Order(1)
public class AttributeService
    extends ARemoteEntityService<
        Attribute,
        Integer,
        R_get_dogma_attributes_attribute_id,
        AttributeRepository> {

	@Override
	protected Attribute create(Integer id) {
		if (id == 0) {
			throw new UnsupportedOperationException();
		}
		Attribute ret = new Attribute();
		ret.setId(id);
		return ret;
	}

	@Override
	protected Requested<R_get_dogma_attributes_attribute_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_dogma_attributes(id, properties);
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_dogma_attributes(p).mapBody(List::of);
	}

	public static record RequiredSkill(int id, int level) {
	}

	public List<RequiredSkill> requiredSkills(Iterable<Integer> typeIds) {
		return repo().requiredSkills(typeIds).stream()
				.map(arr -> new RequiredSkill(((Number) arr[0]).intValue(), ((Number) arr[1]).intValue()))
				.toList();
	}

}
