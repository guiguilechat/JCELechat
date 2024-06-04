package fr.guiguilechat.jcelechat.libs.spring.items.effect;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModifierRepository extends JpaRepository<Modifier, Long> {

	public int deleteByEffect(Effect effect);

}
