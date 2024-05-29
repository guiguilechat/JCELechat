package fr.guiguilechat.jcelechat.libs.spring.items.attribute;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.services.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		ret.setRemoteId(id);
		return ret;
	}

	@Override
	protected Requested<R_get_dogma_attributes_attribute_id> fetchData(Integer id,
	    Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_dogma_attributes(id, properties);
	}

	@Value("${esi.items.attributes.skipfetch:false}")
	private boolean skipFetch = false;

	@Scheduled(fixedDelayString = "${esi.items.attributes.fetchdelay:4000}", fixedRateString = "${esi.items.attributes.fetchcycle:3600000}")
	protected void addAllAttributes() {
		if (skipFetch) {
			return;
		}
		Requested<Integer[]> response = ESIRawPublic.INSTANCE.get_dogma_attributes(null);
		if (response != null && response.isOk()) {
			createIfAbsent(List.of(response.getOK()), false);
		} else {
			log.warn("received error when fetching attributes : "+response);
		}
	}

}
