package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.lpexchange;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LPExchangeRepository extends JpaRepository<LPExchange, Long> {

	List<LPExchange> findAllByTargetCorporationId(int targetCorporationId);

}
