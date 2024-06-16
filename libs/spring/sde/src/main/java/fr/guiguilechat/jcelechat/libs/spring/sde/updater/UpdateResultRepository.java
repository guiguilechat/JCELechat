package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.UpdateResult.STATUS;

public interface UpdateResultRepository extends JpaRepository<UpdateResult, Long> {

	public UpdateResult findTop1ByStatusOrderByStartedDateDesc(STATUS status);

	@Query("""
update SdeUpdaterResult set status=:newStatus where status=:status
""")
	@Modifying
	public void changeStatusFromTo(STATUS status, STATUS newStatus);

}
