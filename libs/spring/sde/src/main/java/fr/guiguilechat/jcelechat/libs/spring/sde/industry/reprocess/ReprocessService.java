package fr.guiguilechat.jcelechat.libs.spring.sde.industry.reprocess;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ReprocessService {

	private final ReprocessRepository repo;


	public List<Reprocess> productOf(Type reprocessed) {
		return repo.findByReprocessed(reprocessed);
	}

	public List<Reprocess> sourceOf(Type product) {
		return repo.findByProduct(product);
	}

}
