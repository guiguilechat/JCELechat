package fr.guiguilechat.jcelechat.libs.spring.evehistory.services.market;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketOrder;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market.MarketOrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketAnalyzeService {

	@Autowired
	private MarketFetchLineService mflService;

	@Autowired
	private MarketFetchResultService mfrService;

	@Autowired
	private MarketOrderService orderService;

	@Autowired
	private MarketOrderRepository orderRepo;

	@Async
	@Transactional
	public CompletableFuture<Void> analyzeLines(MarketFetchResult result, MarketFetchResult follow) {
		long startTime = System.currentTimeMillis();
		List<MarketFetchLine> updatedLines = new ArrayList<>();
		List<MarketFetchLine> deletedLines = new ArrayList<>();
		List<MarketOrder> updatedOrders = new ArrayList<>();
		int created = 0;
		int lastOccurence = 0;
		int changed = 0;
		List<Object[]> linesFetched = mflService.listOrderChanges(result, follow);
		long fetchedTime = System.currentTimeMillis();
		for (Object[] arr : linesFetched) {
			MarketFetchLine line = (MarketFetchLine) arr[0];
			MarketOrder ord = (MarketOrder) arr[1];
			MarketFetchLine before = (MarketFetchLine) arr[2];
			MarketFetchLine after = (MarketFetchLine) arr[3];
			if (ord == null) {
				Optional<MarketOrder> repoOrder = orderRepo.findById(line.getOrder().order_id);
				throw new NullPointerException("order of line id=" + line.getId() + " order_id=" + line.getOrder().order_id
						+ " is null , repo returns " + repoOrder + ", line invalid=" + line.isInvalid());
			}

			line.setSoldTo(result.getLastModified());
			if (before != null && before != line) {
				line.setPreviousLine(before);
				line.setPriceChg(before.getOrder().price != line.getOrder().price);
				line.setSold(before.getOrder().volume_remain - line.getOrder().volume_remain);
				line.setSoldFrom(before.getSoldTo().isAfter(line.getIssuedDate())
						? before.getSoldTo()
						: line.getIssuedDate());
				if (line.getSold() > 0 || line.isPriceChg()) {
					changed++;
				}
			} else {
				line.setCreation(true);
				line.setSold(line.getOrder().volume_total - line.getOrder().volume_remain);
				line.setSoldFrom(line.getIssuedDate());
				created++;
			}
			line.setSoldDate(Instant.ofEpochMilli((line.getSoldFrom().toEpochMilli() +
					line.getSoldTo().toEpochMilli()) / 2));

			if (after == null) {
				line.setRemoval(true);
				line.setRemovalTo(follow.getLastModified());
				line.setRemovalFrom(result.getLastModified());
				Instant eol = line.getIssuedDate().plus(Duration.ofDays(line.getOrder().duration));
				// use !isBefore to accept equality . Same for !isAfter
				if (!eol.isBefore(line.getRemovalFrom()) &&
						!eol.isAfter(line.getRemovalTo())) {
					line.setEol(true);
					line.setRemovalDate(eol);
				} else {
					line.setEol(false);
					line.setRemovalDate(
							Instant.ofEpochMilli((line.getRemovalFrom().toEpochMilli() +
									line.getRemovalTo().toEpochMilli()) / 2));
				}
				lastOccurence++;
			}
			if (line.isRemoval() || line.isCreation() || line.isPriceChg() ||
					line.getSold() > 0) {
				updatedLines.add(line);
				ord.setLastLine(line);
				updatedOrders.add(ord);
			} else {
				deletedLines.add(line);
			}
		}
		long analyzedTime = System.currentTimeMillis();
		mflService.saveAll(updatedLines);
		orderService.saveAll(updatedOrders);
		mflService.deleteAll(deletedLines);
		result.setLinesUpdated(changed);
		result.setStatus(STATUS.LINESANALYZED);
		mfrService.save(result);
		long endTime = System.currentTimeMillis();
		log.info(
				"analyze of marketfetch=" + result.getId() + " regionid=" +
						result.getRegion().getRegionId() + " in "
						+ (endTime - startTime)
						+ "ms (fetch=" + (fetchedTime - startTime) + " analyze=" + (analyzedTime - fetchedTime) + " save=" + (endTime - analyzedTime)
						+ ") : created:" + created + " changed:" + changed + " last:" + lastOccurence + " delete:" + deletedLines.size());
		return CompletableFuture.completedFuture(null);
	}

}
