package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypeEffectRepository extends JpaRepository<TypeEffect, Long> {

	@Modifying
	@Query("delete from #{#entityName}")
	void delete();


}
