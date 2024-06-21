package fr.guiguilechat.jcelechat.libs.spring.items.effect;

import java.util.Collection;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.effects")
public class ModifierService {

	private final ModifierRepository repo;

	public void deleteByEffects(Collection<Effect> types) {
		repo.deleteByEffectId(types.stream().map(Effect::getId).toList());
	}

	public Modifier save(Modifier data) {
		return repo.save(data);
	}

	public List<Modifier> saveAll(Iterable<Modifier> data) {
		return repo.saveAll(data);
	}

}
