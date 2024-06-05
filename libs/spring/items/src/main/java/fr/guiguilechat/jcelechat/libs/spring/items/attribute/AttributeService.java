package fr.guiguilechat.jcelechat.libs.spring.items.attribute;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AttributeService
    extends ARemoteFetchedResourceService<
        Attribute,
        Integer,
        R_get_dogma_attributes_attribute_id,
        AttributeRepository> {

	@Override
	protected Attribute create(Integer id) {
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

	@Value("${esi.items.attribute.update.skip:false}")
	@Getter
	private boolean skipUpdate = false;

}
