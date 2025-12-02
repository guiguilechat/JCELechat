package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.trade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporationTradeRepository extends JpaRepository<CorporationTrade, Long> {

	List<CorporationTrade> findByCorporationId(int corporationId);

	List<CorporationTrade> findByTypeId(int typeId);

}
