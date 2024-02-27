package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.repositories.LPStoreCorporationRepository;
import jakarta.transaction.Transactional;

@Service
public class LPStoreCorporationService {

	@Autowired
	private LPStoreCorporationRepository repo;

	public List<LPStoreCorporation> listActive(boolean active) {
		return repo.findAllByDisabled(!active);
	}

	public LPStoreCorporation save(LPStoreCorporation entity) {
		return repo.save(entity);
	}

	public void activate(int corporationId) {
		Optional<LPStoreCorporation> active = repo.findByCorporationIdAndDisabled(corporationId, false);
		if (active.isPresent()) {
			return;
		}
		Optional<LPStoreCorporation> inactive = repo.findByCorporationIdAndDisabled(corporationId, true);
		if (inactive.isPresent()) {
			inactive.get().setDisabled(false);
			repo.save(inactive.get());
			return;
		}
		repo.save(
				LPStoreCorporation.builder().corporationId(corporationId).disabled(false).nextFetch(Instant.now()).build());
	}

	public void inactive(int corporationId) {
		Optional<LPStoreCorporation> active = repo.findByCorporationIdAndDisabled(corporationId, false);
		if (active.isPresent()) {
			active.get().setDisabled(true);
			repo.save(active.get());
		}
	}

	String lastEtag = null;

	@Transactional
	public Requested<Integer[]> fetchCorporations() {
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<Integer[]> rids = ESIStatic.INSTANCE.get_corporations_npccorps(properties);
		if (rids.isOk()) {
			Set<Integer> ids = Set.of(rids.getOK());
			List<LPStoreCorporation> disable = repo.findByCorporationIdNotInAndDisabled(ids, false);
			for (LPStoreCorporation c : disable) {
				inactive(c.getCorporationId());
			}
			for (Integer i : ids) {
				activate(i);
			}
			lastEtag = rids.getETag();
		}
		return rids;
	}

	public List<LPStoreCorporation> nextFetch() {
		return repo.findAllByDisabledAndNextFetchLessThan(false, Instant.now());
	}

}
