package fr.guiguilechat.jcelechat.libs.spring.sde.updater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.model.UpdateResult.STATUS;

public interface UpdateResultRepository extends JpaRepository<UpdateResult, Long> {

	public boolean existsByStatus(STATUS status);

}
