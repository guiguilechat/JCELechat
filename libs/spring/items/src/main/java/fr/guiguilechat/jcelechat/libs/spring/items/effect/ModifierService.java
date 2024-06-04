package fr.guiguilechat.jcelechat.libs.spring.items.effect;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ModifierService {

	private final ModifierRepository repo;

	public void deleteForEffect(Effect effect) {
		repo.deleteByEffect(effect);
	}

	public Modifier save(Modifier data) {
		return repo.save(data);
	}

	public List<Modifier> saveAll(Iterable<Modifier> data) {
		return repo.saveAll(data);
	}

}
