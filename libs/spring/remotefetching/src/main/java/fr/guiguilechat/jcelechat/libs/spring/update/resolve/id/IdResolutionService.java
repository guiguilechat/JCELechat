package fr.guiguilechat.jcelechat.libs.spring.update.resolve.id;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.LocalEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IdResolutionService
		extends LocalEntityService<IdResolution, Integer, IdResolutionRepository> {

	@Override
	protected IdResolution create(Integer entityId) {
		IdResolution ret = new IdResolution();
		ret.setId(entityId);
		return ret;
	}

	// service usage

	public String name(int id) {
		Optional<IdResolution> opt = repo().findById(id);
		if (opt != null && opt.isPresent()) {
			IdResolution data = opt.get();
			if (data != null && data.isFetched() && data.getName() != null) {
				return data.getName();
			}
		}
		return "unresolved:" + id;
	}

}
