package fr.guiguilechat.jcelechat.libs.spring.affiliations.faction;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class FactionInfoService {

	@Getter
	@Accessors(fluent = true)
	private final FactionInfoRespository repo;

	@Async
	@Transactional
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

	public Map<Integer, FactionInfo> listAll() {
		return repo.findAll().stream().collect(Collectors.toMap(FactionInfo::getFactionId, fi -> fi));
	}

	public FactionInfo save(FactionInfo data) {
		return repo.save(data);

	}

}
