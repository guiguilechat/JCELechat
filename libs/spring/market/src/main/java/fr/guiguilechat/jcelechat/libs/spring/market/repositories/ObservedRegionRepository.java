package fr.guiguilechat.jcelechat.libs.spring.market.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.market.model.ObservedRegion;

public interface ObservedRegionRepository extends JpaRepository<ObservedRegion, Integer> {

	public List<ObservedRegion> findByActiveTrue();

}
