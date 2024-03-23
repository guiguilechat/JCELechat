package fr.guiguilechat.jcelechat.programs.spring.eveproxy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import fr.guiguilechat.jcelechat.libs.spring.mer.model.Kill;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.model.KillData;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.repositories.KillDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KillDataService {

	final private KillDataRepository repo;

	public void saveAll(Iterable<KillData> entities) {
		entities.forEach(KillData::updateValues);
		repo.saveAll(entities);
	}

	public KillData save(KillData entity) {
		entity.updateValues();
		return repo.save(entity);
	}

	public static record KDCreation(Kill kill, SolarSystem solarSystem, Type victimType) {

	}

	@Value("${eveproxy.sync.killpagesize:100000}")
	private int missingPagesize;

	public List<KDCreation> findMissing(long lastid) {
		return repo.findMissing(PageRequest.ofSize(missingPagesize), lastid).stream()
				.map(l -> new KDCreation((Kill) l[0], (SolarSystem) l[1], (Type) l[2])).toList();
	}

	private long lastiddone = 0l;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void createMissing() {
		long start = System.currentTimeMillis();
		List<KDCreation> missings = findMissing(lastiddone);
		long fetched = System.currentTimeMillis();
		if (missings.size() > 0) {
			log.info("creating missing data for " + missings.size() + " kills fetched in " + (fetched - start) + "ms");
			repo.saveAll(missings.stream().map(kdc -> KillData.of(kdc.kill(), kdc.solarSystem(), kdc.victimType())).toList());
			log.info(" created missing data");
			lastiddone = missings.get(missings.size() - 1).kill().getId();
		}
	}

}
