package fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.character.CharacterAffiliation;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.character.CharacterAffiliationService.AffiliationListener;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resolve.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resolve.IdResolutionListener;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CorporationInfoService extends
    ARemoteFetchedResourceService<CorporationInfo, Integer, R_get_corporations_corporation_id, CorporationInfoRepository>
    implements IdResolutionListener, AffiliationListener {

	@Override
	protected CorporationInfo create(Integer entityId) {
		CorporationInfo ret = new CorporationInfo();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_corporations_corporation_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_corporations(id, properties);
	}

	private Set<Integer> npcCorps = null;

	private Instant npcCorpsExpires = null;

	/**
	 * @return cached set of NPC corporation, provided by ESI
	 */
	public Set<Integer> npcCorps() {
		if (npcCorpsExpires == null || npcCorpsExpires.isBefore(Instant.now())) {
			synchronized (this) {
				if (npcCorpsExpires == null || npcCorpsExpires.isAfter(Instant.now())) {
					Requested<Integer[]> response = ESIRawPublic.INSTANCE.get_corporations_npccorps(null);
					if (response.isOk()) {
						npcCorps = Set.of(response.getOK());
						npcCorpsExpires = response.getExpiresInstant();
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

	@Value("${esi.corporations.skipnpc:false}")
	private boolean skipImportNPC = false;

	@Async
	@EventListener(ApplicationStartedEvent.class)
	protected void addNPCCorp() {
		if (!skipImportNPC) {
			createIfAbsent(npcCorps());
		}
	}

	public Map<Integer, CorporationInfo> allById() {
		return repo().findAll().stream().collect(Collectors.toMap(CorporationInfo::getCorporationId, c -> c));
	}

	@Override
	public void onNewIdResolution(IdResolution idResolution) {
		if (idResolution.getCategory() == post_universe_names_category.corporation) {
			createIfAbsent(idResolution.getId());
		}
	}

	@Override
	public void onNewAffiliation(CharacterAffiliation received) {
		if (received.getCorporationId() > 0) {
			createIfAbsent(received.getCorporationId());
		}
	}

}
