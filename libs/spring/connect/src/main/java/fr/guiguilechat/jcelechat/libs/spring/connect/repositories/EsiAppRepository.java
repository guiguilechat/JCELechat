package fr.guiguilechat.jcelechat.libs.spring.connect.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiApp;

public interface EsiAppRepository extends JpaRepository<EsiApp, Long> {

	public Optional<EsiApp> findByAppIdAndAppBase64(String appId, String appBase64);

}
