package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdate.STATUS;

public interface SdeUpdateRepository extends JpaRepository<SdeUpdate, Long> {

	public SdeUpdate findTop1ByStatusOrderByStartedDateDesc(STATUS status);

	@Query("""
update SdeUpdaterResult set status=:newStatus where status=:status
""")
	@Modifying
	public void changeStatusFromTo(STATUS status, STATUS newStatus);

}
