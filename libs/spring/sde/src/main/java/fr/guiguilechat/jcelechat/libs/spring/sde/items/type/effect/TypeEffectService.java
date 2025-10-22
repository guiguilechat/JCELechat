package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Service
public class TypeEffectService {

	@Getter(value = AccessLevel.PACKAGE)
	@Accessors(fluent = true)
	private final TypeEffectRepository repo;

	public void delete() {
		repo().delete();
	}

	public TypeEffect save(TypeEffect entity) {
		return repo().save(entity);
	}

	public List<TypeEffect> saveAll(Iterable<TypeEffect> entities) {
		return repo().saveAll(entities);
	}

}
