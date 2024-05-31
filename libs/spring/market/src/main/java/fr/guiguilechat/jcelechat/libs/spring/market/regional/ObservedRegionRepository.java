package fr.guiguilechat.jcelechat.libs.spring.market.regional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservedRegionRepository extends JpaRepository<ObservedRegion, Integer> {

	public List<ObservedRegion> findByActiveMarketTrue();

	public List<ObservedRegion> findByActiveContractsTrue();

}
