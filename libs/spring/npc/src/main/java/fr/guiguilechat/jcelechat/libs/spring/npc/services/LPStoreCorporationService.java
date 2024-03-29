package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.repositories.LPStoreCorporationRepository;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.UpdateScheduler.CorporationsUpdateListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LPStoreCorporationService implements CorporationsUpdateListener {

	final private LPStoreCorporationRepository repo;

	public Optional<LPStoreCorporation> byId(int corporationId) {
		return repo.findById(corporationId);
	}

	@Cacheable("lpCorpActive")
	public List<LPStoreCorporation> listActive(boolean active) {
		return repo.findAllByDisabled(!active);
	}

	public LPStoreCorporation save(LPStoreCorporation entity) {
		return repo.save(entity);
	}

	@Async
	public CompletableFuture<LPStoreCorporation> activate(int corporationId) {
		LPStoreCorporation ret = null;
		Optional<LPStoreCorporation> active = repo.findByCorporationIdAndDisabled(corporationId, false);
		if (active.isPresent()) {
			ret = active.get();
		} else {
			Optional<LPStoreCorporation> inactive = repo.findByCorporationIdAndDisabled(corporationId, true);
			if (inactive.isPresent()) {
				inactive.get().setDisabled(false);
				repo.save(inactive.get());
				ret = inactive.get();
			} else {
				ret = repo.save(
						LPStoreCorporation.builder()
								.corporationId(corporationId)
								.disabled(false)
								.name(null)
								.nextFetch(Instant.now()).build());
			}
		}
		if (ret != null && ret.getName() == null) {
			Requested<R_get_corporations_corporation_id> corpdataReq = ESIStatic.INSTANCE.get_corporations(corporationId,
					null);
			if (corpdataReq.isOk()) {
				R_get_corporations_corporation_id corpdata = corpdataReq.getOK();
				ret.setName(corpdata.name);
				repo.save(ret);
			} else {
				log.error("while fetching data corp " + corporationId + " : " + corpdataReq.getError());
			}
		}
		return CompletableFuture.completedFuture(ret);
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
		long startMs = System.currentTimeMillis();
		Map<String, String> properties = new HashMap<>();
		if (lastEtag != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lastEtag);
		}
		Requested<Integer[]> rids = ESIStatic.INSTANCE.get_corporations_npccorps(properties);
		long fetchMs = System.currentTimeMillis();
		if (rids.isOk()) {
			Set<Integer> ids = Set.of(rids.getOK());
			List<LPStoreCorporation> disable = repo.findByCorporationIdNotInAndDisabled(ids, false);
			long listDisableMs = System.currentTimeMillis();
			for (LPStoreCorporation c : disable) {
				inactive(c.getCorporationId());
			}
			long disableMs = System.currentTimeMillis();
			Map<Integer, CompletableFuture<LPStoreCorporation>> futures = ids.parallelStream()
					.collect(Collectors.toMap(i -> i, this::activate));
			futures.values().forEach(CompletableFuture::join);
			long activateMs = System.currentTimeMillis();
			log.info("updated corporations in fetch=" + (fetchMs - startMs) + "ms "
					+ "listdisable=" + (listDisableMs - fetchMs) + "ms "
					+ "disable=" + (disableMs - listDisableMs) + "ms "
					+ "activate=" + (activateMs - disableMs) + "ms ");
			lastEtag = rids.getETag();
		}
		return rids;
	}

	public List<LPStoreCorporation> nextFetch() {
		return repo.findAllByDisabledFalseAndNextFetchLessThan(Instant.now());
	}

	public LPStoreCorporation prevCorp(LPStoreCorporation corp) {
		return corp == null ? null : repo.findTop1ByNameLessThanOrderByNameDesc(corp.getName());
	}

	public LPStoreCorporation nextCorp(LPStoreCorporation corp) {
		return corp == null ? null : repo.findTop1ByNameGreaterThanOrderByNameAsc(corp.getName());
	}

	public static List<String> CORPORATIONS_CACHES = List.of(
			"lpCorpActive");

	@Override
	public List<String> listCorporationsCaches() {
		return CORPORATIONS_CACHES;
	}

}
