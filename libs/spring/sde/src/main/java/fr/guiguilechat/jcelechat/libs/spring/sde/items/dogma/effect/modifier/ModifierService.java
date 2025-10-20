package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect.modifier;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ModifierService {

	private final ModifierRepository repo;

	public Modifier save(Modifier data) {
		return repo.save(data);
	}

	public List<Modifier> saveAll(Iterable<Modifier> data) {
		return repo.saveAll(data);
	}

	public void deleteAll() {
		repo.delete();
	}

}
