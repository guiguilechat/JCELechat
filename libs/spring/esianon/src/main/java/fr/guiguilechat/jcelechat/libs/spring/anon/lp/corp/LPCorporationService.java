package fr.guiguilechat.jcelechat.libs.spring.anon.lp.corp;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporationService;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LPCorporationService extends
		RemoteEntityService<LPCorporation, Integer, LPCorporationRepository> {

	@Lazy
	private final NpcCorporationService npcCorporationService;

	@Override
	protected LPCorporation create(Integer entityId) {
		LPCorporation ret = new LPCorporation();
		ret.setId(entityId);
		ret.setCorporation(npcCorporationService.createIfAbsent(entityId));
		return ret;
	}

	// external usage

	public List<LPCorporation> allWithOffers() {
		return repo().findByNbOffersGreaterThanOrderByCorporationNameAsc(0);
	}

	public LPCorporation prevCorp(String name) {
		return repo().findTop1ByCorporationNameLessThanOrderByCorporationNameDesc(name);
	}

	public LPCorporation nextCorp(String name) {
		return repo().findTop1ByCorporationNameGreaterThanOrderByCorporationNameAsc(name);
	}

}
