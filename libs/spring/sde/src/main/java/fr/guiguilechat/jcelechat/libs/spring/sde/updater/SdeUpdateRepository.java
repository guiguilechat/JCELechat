package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdate.Status;

public interface SdeUpdateRepository extends JpaRepository<SdeUpdate, Long> {

	SdeUpdate findTop1ByStatusOrderByStartedDateDesc(Status status);

}
