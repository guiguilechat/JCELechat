package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.Corporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.repositories.CorporationRepository;
import fr.guiguilechat.jcelechat.libs.spring.templates.services.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CorporationService extends
    ARemoteFetchedResourceService<Corporation, Integer, R_get_corporations_corporation_id, CorporationRepository> {

	@Override
	protected Corporation create(Integer entityId) {
		Corporation ret = new Corporation();
		ret.setCorporationId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_corporations_corporation_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_corporations(id, properties);
	}

	@Async
	@EventListener(ApplicationStartedEvent.class)
	protected void addNPCCorp() {
		Requested<Integer[]> corpIds = ESIRawPublic.INSTANCE.get_corporations_npccorps(null);
		if (corpIds.isOk()) {
			for (int i : corpIds.getOK()) {
				createIfMissing(i);
			}
		}
	}

	public Map<Integer, Corporation> allById() {
		return repo().findAll().stream().collect(Collectors.toMap(Corporation::getCorporationId, c -> c));
	}

}
