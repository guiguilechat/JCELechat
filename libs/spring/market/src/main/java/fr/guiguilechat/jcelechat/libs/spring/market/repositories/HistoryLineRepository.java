package fr.guiguilechat.jcelechat.libs.spring.market.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryLine;
import fr.guiguilechat.jcelechat.libs.spring.market.model.HistoryReq;

public interface HistoryLineRepository extends JpaRepository<HistoryLine, Long> {

	public void deleteByHistoryReq(HistoryReq historyReq);

	public List<HistoryLine> findByHistoryReqRegionIdAndHistoryReqTypeId(int regionId, int typeId);

}
