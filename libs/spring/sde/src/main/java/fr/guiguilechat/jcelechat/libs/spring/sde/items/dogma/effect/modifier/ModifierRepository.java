package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect.modifier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ModifierRepository extends JpaRepository<Modifier, Long> {

	@Modifying
	@Query("delete from #{#entityName}")
	void delete();

}
