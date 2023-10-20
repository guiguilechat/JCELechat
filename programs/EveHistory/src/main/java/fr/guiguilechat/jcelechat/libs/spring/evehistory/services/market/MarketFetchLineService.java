package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.MarketFetchLineRepository;
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
	public void analyzeLines(MarketFetchResult result, MarketFetchResult follow) {
		long start = System.currentTimeMillis();
		List<MarketFetchLine> updated = new ArrayList<>();
		List<MarketFetchLine> deleted = new ArrayList<>();
		Map<MarketFetchLine, MarketFetchLine> result2Previous = new HashMap<>();
		List<Object[]> changes = repo.listOrderChanges(result);
		int created = 0;
		int last = 0;
		int changed = 0;
		for (Object[] o : changes) {
			MarketFetchLine before = (MarketFetchLine) o[0],
					line = (MarketFetchLine) o[1],
					after = (MarketFetchLine) o[2];

			line.setSoldTo(result.getLastModified());
			if (before != null) {
				result2Previous.put(line, before);
				line.setPriceChg(before.getOrder().price != line.getOrder().price);
				int sold = before.getOrder().volume_remain - line.getOrder().volume_remain;
				line.setSold(sold);
				line.setSoldFrom(before.getSoldTo().isAfter(line.getIssuedDate())
						? before.getSoldTo()
								: line.getIssuedDate());
				if (sold > 0 || line.isPriceChg()) {
					changed++;
				}
			} else {
				line.setCreation(true);
				line.setSold(line.getOrder().volume_total - line.getOrder().volume_remain);
				line.setSoldFrom(line.getIssuedDate());
				created++;
			}
			if (after == null) {
				line.setRemoval(true);
				line.setRemovalTo(follow.getLastModified());
				line.setRemovalFrom(result.getLastModified());
				last++;
			}
			if (line.isRemoval() || line.isCreation() || line.isPriceChg() || line.getSold() > 0) {
				updated.add(line);
			} else {
				deleted.add(line);
			}
		}
		// set the previous to a non-deleted one
		for (MarketFetchLine line : updated) {
			MarketFetchLine previous = result2Previous.get(line);
			while (previous != null && deleted.contains(previous)) {
				previous = result2Previous.get(previous);
			}
			line.setPreviousLine(previous);
		}
		saveAll(updated);
		repo.deleteAll(deleted);
		long end = System.currentTimeMillis();
		log.info(
				"analyze of marketfetch=" + result.getId() + " regionid=" + result.getRegionId() + " in " + (end - start)
				+ "ms : created:" + created + " changed:" + changed + " last:" + last + " delete:" + deleted.size());
	}

	/**
	 * @return the number of orders linked to the result
	 */
	public int countOrders(MarketFetchResult result) {
		return repo.countByFetchResult(result);
	}

	public List<MarketFetchLine> listUpdates(int regionId, int typeId, Instant from, Instant to) {
		return repo.listPriceChanges(regionId, typeId, from, to);
	}

}
