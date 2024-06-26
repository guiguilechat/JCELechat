package fr.guiguilechat.jcelechat.libs.spring.market.history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryLineRepository extends JpaRepository<HistoryLine, Long> {

	public void deleteByHistoryReq(HistoryReq historyReq);

	public List<HistoryLine> findByHistoryReqRegionIdAndHistoryReqTypeId(int regionId, int typeId);

}
