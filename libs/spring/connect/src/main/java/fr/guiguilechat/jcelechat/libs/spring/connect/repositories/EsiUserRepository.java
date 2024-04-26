package fr.guiguilechat.jcelechat.libs.spring.connect.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiApp;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiUser;


public interface EsiUserRepository extends JpaRepository<EsiUser, Long> {

	List<EsiUser> findAllByAppAndCharacterIdAndCanceledFalse(EsiApp app, int characterId);

	List<EsiUser> findAllByCharacterIdAndCanceledFalse(int characterId);

}
