package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityRepository;

public interface HistoryReqRepository extends RemoteNumberEntityRepository<HistoryReq, Long> {

	List<HistoryReq> findByTypeId(int typeId);

	/**
	 * list all the existing (type id, regionid) couples from market lines, joined
	 * to their corresponding history requirement (null if absent).<br />
	 * Only lists the triplets where requirement is null or not active
	 */
	@Query("""
select
	re.tid,
	re.rid,
	hr
from
	(select distinct
		typeId as tid,
		fetchResource.id as rid
	from EsiTradeMarketLine
	) re
	left join #{#entityName} hr
		on re.tid=hr.type.id
		and re.rid=hr.region.id
where
	hr is null
	or not hr.fetchActive
""")
	List<Object[]> listRequiredEntries();

}
