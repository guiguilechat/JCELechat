package fr.guiguilechat.jcelechat.libs.spring.connect.corporation;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.templates.services.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CorporationInfoService extends
    ARemoteFetchedResourceService<CorporationInfo, Integer, R_get_corporations_corporation_id, CorporationInfoRepository> {

	@Override
	protected CorporationInfo create(Integer entityId) {
		CorporationInfo ret = new CorporationInfo();
		ret.setCorporationId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_corporations_corporation_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_corporations(id, properties);
	}

	private Set<Integer> npcCorps = null;

	private Instant npcCorpsExpires = null;

	/**
	 * @return cachhed set of NPC corporation, provided by ESI
	 */
	public Set<Integer> npcCorps() {
		if (npcCorpsExpires == null || npcCorpsExpires.isBefore(Instant.now())) {
			synchronized (this) {
				if (npcCorpsExpires == null || npcCorpsExpires.isAfter(Instant.now())) {
					Requested<Integer[]> response = ESIRawPublic.INSTANCE.get_corporations_npccorps(null);
					if (response.isOk()) {
						npcCorps = Set.of(response.getOK());
						npcCorpsExpires = response.getExpiresInstant();
						// System.err.println("set npccorps to " + npcCorps.size() + " entries, expires
						// at " + npcCorpsExpires);
					} else {
						npcCorpsExpires = Instant.now().plusSeconds(20);
					}
				}
			}
		}
		return npcCorps;
	}

	/**
	 * @return true iff the id belongs to the list of NPC corporation, as provided
	 *           by ESI.
	 */
	public boolean isNpcCorp(int id) {
		return npcCorps().contains(id);
	}

	@Async
	@EventListener(ApplicationStartedEvent.class)
	protected void addNPCCorp() {
		// createIfMissing(npcCorps(), false);
	}

	public Map<Integer, CorporationInfo> allById() {
		return repo().findAll().stream().collect(Collectors.toMap(CorporationInfo::getCorporationId, c -> c));
	}

}
