package fr.guiguilechat.jcelechat.libs.spring.evehistory.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.MarketFetchLineRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketFetchLineService {

	@Autowired
	private MarketFetchLineRepository repo;

	public void updateIssueDate(MarketFetchLine line) {
		if (line.getIssuedDate() == null) {
			if (line.getOrder().issued != null) {
				line.setIssuedDate(ESITools.convertDate(line.getOrder().issued).toInstant());
			}
// line.getOrder().issued = null;
		}
	}

	public void saveAll(List<MarketFetchLine> list) {
		for (MarketFetchLine mfl : list) {
			updateIssueDate(mfl);
		}
		repo.saveAll(list);
	}

	public void save(MarketFetchLine line) {
		updateIssueDate(line);
		repo.save(line);
	}

	/**
	 * analyze the lines linked to a given market fetch result. To make sense, that
	 * result must be followed by a successful one.
	 * The lines are checked to find a previous one and older one for same order id,
	 * and if previous one exists if the volume or price has changed.
	 * Then the lines that meet none of those criterias are deleted, while those
	 * which do are udpated.
	 */
	public void analyzeLines(MarketFetchResult result) {
		List<MarketFetchLine> updated = new ArrayList<>();
		List<MarketFetchLine> deleted = new ArrayList<>();
		List<Object[]> changes = repo.listOrderChanges(result);
		for (Object[] o : changes) {
			MarketFetchLine before = (MarketFetchLine) o[0],
					line = (MarketFetchLine) o[1],
					after = (MarketFetchLine) o[2];
			line.setCreated(before == null);
			if (before != null) {
				line.setPrevious(before);
				line.setPriceChg(before.getOrder().price != line.getOrder().price);
				line.setVolumeChg(before.getOrder().volume_remain != line.getOrder().volume_remain);
			}
			line.setLast(after == null);
			if (line.isLast() || line.isCreated() || line.isPriceChg() || line.isVolumeChg()) {
				updated.add(line);
			} else {
				deleted.add(line);
			}
		}
		saveAll(updated);
		repo.deleteAll(deleted);
		log.info(
				"analyze of market fetch " + result.getId() + " orders : keep:" + updated.size() + " delete:" + deleted.size());
	}

	/**
	 * @return the number of orders linked to the result
	 */
	public int countOrders(MarketFetchResult result) {
		return repo.countByFetchResult(result);
	}

}
