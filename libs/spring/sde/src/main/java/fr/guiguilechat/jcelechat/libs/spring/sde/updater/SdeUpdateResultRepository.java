package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateResult.Status;

public interface SdeUpdateResultRepository extends JpaRepository<SdeUpdateResult, Long> {

	SdeUpdateResult findTop1ByStatusOrderByStartedDateDesc(Status status);

}
