package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeResult.Status;

public interface SdeResultRepository extends JpaRepository<SdeResult, Long> {

	SdeResult findTop1ByStatusOrderByStartedDateDesc(Status status);

}
