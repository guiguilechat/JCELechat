package fr.guiguilechat.jcelechat.libs.spring.connect.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EsiUserRepository extends JpaRepository<EsiUser, Long> {

	List<EsiUser> findAllByAppAndCharacterIdAndCanceledFalse(EsiApp app, int characterId);

	List<EsiUser> findAllByCharacterIdAndCanceledFalse(int characterId);

}
