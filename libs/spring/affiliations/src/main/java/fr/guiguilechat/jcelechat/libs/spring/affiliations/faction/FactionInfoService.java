package fr.guiguilechat.jcelechat.libs.spring.affiliations.faction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FactionInfoService {

	@Getter
	@Accessors(fluent = true)
	private final FactionInfoRepository repo;

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public FactionInfo createIfAbsent(int entityId) {
		FactionInfo ret = repo().findById(entityId).orElse(null);
		if (ret == null) {
			synchronized (repo()) {
				ret = repo().findById(entityId).orElse(null);
				if (ret == null) {
					ret = new FactionInfo();
					ret.setFactionId(entityId);
					repo.save(ret);
				}
			}
		}
		return ret;
	}

	public Map<Integer, FactionInfo> allById() {
		return repo.findAll().stream().collect(Collectors.toMap(FactionInfo::getFactionId, fi -> fi));
	}

	public void update(R_get_universe_factions[] data) {
		Map<Integer, FactionInfo> existing = allById();
		Map<Integer, CorporationInfo> idToCorporationInfo = 
				corporationInfoService.createIfAbsent(
						Stream.of(data).map(d->d.corporation_id)
						.distinct().filter(i->i>0)
						.toList());
		for (R_get_universe_factions factionData : data) {
			FactionInfo found = existing.get(factionData.faction_id);
			if (found == null) {
				found = new FactionInfo();
				found.setFactionId(factionData.faction_id);
			}
			found.update(factionData);
			found.setCorporation(idToCorporationInfo.get(factionData.corporation_id));
		}
		saveAll(existing.values());
	}

	public FactionInfo save(FactionInfo data) {
		return repo.save(data);
	}

	public List<FactionInfo> saveAll(Iterable<FactionInfo> data) {
		return repo.saveAll(data);
	}

}
