package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DeducedEntityRepository<Entity> extends JpaRepository<Entity, Long> {

	@Modifying
	@Query("delete from	#{#entityName}")
	void delete();

}
