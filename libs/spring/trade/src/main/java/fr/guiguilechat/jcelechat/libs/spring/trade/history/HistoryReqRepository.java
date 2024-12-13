package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface HistoryReqRepository extends IRemoteEntityRepository<HistoryReq, Long> {

	public List<HistoryReq> findByTypeId(int typeId);

}
