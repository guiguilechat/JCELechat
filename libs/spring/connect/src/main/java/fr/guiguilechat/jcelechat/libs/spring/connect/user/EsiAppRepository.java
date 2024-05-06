package fr.guiguilechat.jcelechat.libs.spring.connect.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EsiAppRepository extends JpaRepository<EsiApp, Long> {

	public Optional<EsiApp> findByAppIdAndAppBase64(String appId, String appBase64);

}
