package fr.guiguilechat.jcelechat.libs.spring.gameclient.updater;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.gameclient.updater.GameClientUpdate.Status;

public interface GameClientUpdateRepository extends JpaRepository<GameClientUpdate, Long> {

	GameClientUpdate findTop1ByStatusInOrderByStartedDateDesc(Iterable<Status> status);

}
