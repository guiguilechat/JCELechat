package fr.guiguilechat.jcelechat.libs.spring.industry.activity.modifier;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class IndustryModifierService {

	final private IndustryModifierRepository repo;

	public List<IndustryModifier> saveAll(Iterable<IndustryModifier> entities) {
		return repo.saveAllAndFlush(entities);
	}

	public IndustryModifier save(IndustryModifier entity) {
		return repo.saveAndFlush(entity);
	}

	public List<IndustryModifier> all() {
		return repo.findAll();
	}

}
